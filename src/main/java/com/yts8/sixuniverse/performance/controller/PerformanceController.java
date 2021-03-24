package com.yts8.sixuniverse.performance.controller;

import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.performance.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/host/performance")
public class PerformanceController {

  private final PerformanceService performanceService;

  @GetMapping("/income")
  public String income(HttpSession session, Model model) {
    MemberDto member = (MemberDto) session.getAttribute("member");

    //연 매출
    int yearlyIncomeCount = performanceService.findByYearlyIncomeCount(member.getMemberId());
//    System.out.println("yearlyIncomeCount = " + yearlyIncomeCount);
    if (yearlyIncomeCount != 0) {
      int yearlyIncome = performanceService.findByYearlyIncome(member.getMemberId());
      NumberFormat formatter = NumberFormat.getNumberInstance();
      model.addAttribute("yearlyIncome", formatter.format(yearlyIncome));
    } else {
      model.addAttribute("yearlyIncome", 0);
    }

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

    //리뷰점수
    NumberFormat formatter = new DecimalFormat("0.#");
    double reviewScore = performanceService.findByReviewScore(member.getMemberId());
    model.addAttribute("reviewScore", formatter.format(reviewScore));

    //숙소 개수
    int roomCount = performanceService.findByRoomCount(member.getMemberId());
    model.addAttribute("roomCount", roomCount);

    //취소율
    int reservationCount = performanceService.findByReservationCount(member.getMemberId());
    if (reservationCount != 0) {
      double cancelRatio = performanceService.findByCancelRatio(member.getMemberId());
      model.addAttribute("cancelRatio", cancelRatio);
    } else {
      model.addAttribute("cancelRatio", 0);
    }

    //매출액
    int monthlyIncomeCount = performanceService.findByMonthlyIncomeCount(member.getMemberId());
    if (monthlyIncomeCount != 0) {
      int monthlyIncome = performanceService.findByMonthlyIncome(member.getMemberId());
      model.addAttribute("monthlyIncome", monthlyIncome);
    } else {
      model.addAttribute("monthlyIncome", 0);
    }

    return "performance/super-host";
  }

  @GetMapping("/views")
  public String views(HttpSession session, Model model) {

    MemberDto member = (MemberDto) session.getAttribute("member");
    int reservationMonthlyCount = performanceService.findByReservationMonthlyCount(member.getMemberId());
    model.addAttribute("reservationMonthlyCount", reservationMonthlyCount);

    return "performance/views";
  }


//  @GetMapping ("/#") //후기관리 위치
//  public String review() { return "performance/#";
//  }

}
