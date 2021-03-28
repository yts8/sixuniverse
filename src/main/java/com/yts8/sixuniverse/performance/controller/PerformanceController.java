package com.yts8.sixuniverse.performance.controller;

import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.performance.service.PerformanceService;
import com.yts8.sixuniverse.review.dto.ReviewDto;
import com.yts8.sixuniverse.review.dto.ReviewHostDto;
import com.yts8.sixuniverse.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/host/performance")
public class PerformanceController {

  private final PerformanceService performanceService;
  private final ReviewService reviewService;

  @GetMapping("/income")
  public String income(HttpSession session, Model model) {
    MemberDto member = (MemberDto) session.getAttribute("member");

    //연 매출
    int yearlyIncome = performanceService.findByYearlyIncome(member.getMemberId());
    NumberFormat formatter = NumberFormat.getNumberInstance();
    model.addAttribute("yearlyIncome", formatter.format(yearlyIncome));


    //월별 매출 -> 차트
    ArrayList<Integer> incomeList = new ArrayList<Integer>();
    for (int interval = 0; interval < 12; interval++) {
      int i = performanceService.monthlyIncomeList(member.getMemberId(), interval);
      incomeList.add(i);
    }
    model.addAttribute("incomeList", incomeList);
    return "performance/income";
  }

  @GetMapping("/super-host")
  public String superHost(HttpSession session, Model model) {

    MemberDto member = (MemberDto) session.getAttribute("member");

    //슈퍼호스트 조건 달성 개수
    int condition = 0;

    //리뷰점수
    NumberFormat formatter = new DecimalFormat("0.#");
    double reviewScore = performanceService.findByReviewScore(member.getMemberId());
    model.addAttribute("reviewScore", formatter.format(reviewScore));
    if (reviewScore >= 4.8) {
      condition++;
    }

    //숙소 개수
    int roomCount = performanceService.findByRoomCount(member.getMemberId());
    model.addAttribute("roomCount", roomCount);
    if (roomCount >= 3) {
      condition++;
    }

    //취소율
    int reservationCount = performanceService.findByReservationCount(member.getMemberId());
    if (reservationCount != 0) {
      double cancelRatio = performanceService.findByCancelRatio(member.getMemberId());
      model.addAttribute("cancelRatio", cancelRatio);
      if (cancelRatio < 1.0) {
        condition++;
      }
    } else {
      model.addAttribute("cancelRatio", 0);
    }

    //매출액
    int monthlyIncome = performanceService.findByMonthlyIncome(member.getMemberId());
    NumberFormat formatter2 = NumberFormat.getNumberInstance();
    model.addAttribute("monthlyIncome", formatter2.format(monthlyIncome));
    if (monthlyIncome >= 1900000) {
      condition++;
    }
    
    //슈퍼호스트
    if (condition == 4) {
      performanceService.updateSuperHost(member.getMemberId());
    }

    int isSuperHost = 0;
    String grade = performanceService.findBySuperHostIs(member.getMemberId());
    if (grade.equals("SUPER-HOST")) {
      isSuperHost = 1;
    }
    model.addAttribute("isSuperHost", isSuperHost);

    return "performance/super-host";
  }

  @GetMapping("/views")
  public String views(HttpSession session, Model model) {

    MemberDto member = (MemberDto) session.getAttribute("member");

    int reservationMonthlyCount = performanceService.findByReservationMonthlyCount(member.getMemberId());
    model.addAttribute("reservationMonthlyCount", reservationMonthlyCount);

    int readCount = performanceService.findByHits(member.getMemberId());
    model.addAttribute("readCount", readCount);

    if (readCount != 0) {
      double reservationRatio = reservationMonthlyCount * 100 / readCount;
      model.addAttribute("reservationRatio", reservationRatio);
    } else {
      model.addAttribute("reservationRatio", "0");
    }

    //chart로 보낼 hitsList
    ArrayList<Integer> hitsList = new ArrayList<Integer>();
    for (int interval = 0; interval < 7; interval++) {
      int hits = performanceService.findByHitsList(member.getMemberId(), interval);
      hitsList.add(hits);
    }
    model.addAttribute("hitsList", hitsList);

    return "performance/views";
  }

  @GetMapping("/review")
  public String host(HttpSession session, Model model) {
    model.addAttribute("title", "후기");

    MemberDto member = (MemberDto) session.getAttribute("member");
    Long memberId = member.getMemberId();

    List<ReviewHostDto> reviewHostList = reviewService.reviewHostList(memberId);
    model.addAttribute("reviewHostList", reviewHostList);

    for (ReviewHostDto review : reviewHostList) {
      Long reviewId = review.getReviewId();
      ReviewDto getReview = reviewService.getReview(reviewId);
      model.addAttribute("getReview", getReview);
    }

    NumberFormat formatter = new DecimalFormat("0.#");
    double reviewScore = performanceService.findByReviewScore(member.getMemberId());
    model.addAttribute("reviewScore", formatter.format(reviewScore));

    int reviewCount = reviewService.hostReviewCount(member.getMemberId());
    model.addAttribute("reviewCount", reviewCount);

    LocalDate today = LocalDate.now();
    model.addAttribute("today", today);

    return "review/host-review";
  }

  @PostMapping("/review/update")
  public String hostUpdate(ReviewDto reviewDto) {

    ReviewDto review = reviewService.getReview(reviewDto.getReviewId());

    LocalDate today = LocalDate.now();
    LocalDate reviewRegDate = review.getReviewRegDate();
    LocalDate reviewLimit = reviewRegDate.plusDays(2);
    Period period = Period.between(today, reviewLimit);

    if (period.getDays() < 0 || period.getDays() > 2) {
      return "redirect:/host/performance/review";
    } else {
      reviewService.updateReply(reviewDto);
      return "redirect:/host/performance/review";
    }

  }

  @PostMapping("/review/delete")
  public String hostDelete(ReviewDto reviewDto) {

    ReviewDto review = reviewService.getReview(reviewDto.getReviewId());

    LocalDate today = LocalDate.now();
    LocalDate reviewRegDate = review.getReviewRegDate();
    LocalDate reviewLimit = reviewRegDate.plusDays(2);
    Period period = Period.between(today, reviewLimit);

    if (period.getDays() < 0 || period.getDays() > 2) {
      return "redirect:/host/performance/review";
    } else {
      reviewService.deleteReply(reviewDto);
      return "redirect:/host/performance/review";
    }

  }

}
