package com.yts8.sixuniverse.review.controller;

import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.member.service.MemberService;
import com.yts8.sixuniverse.performance.service.PerformanceService;
import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import com.yts8.sixuniverse.reservation.service.ReservationService;
import com.yts8.sixuniverse.review.dto.ReviewDto;
import com.yts8.sixuniverse.review.dto.ReviewGuestDto;
import com.yts8.sixuniverse.review.dto.ReviewHostDto;
import com.yts8.sixuniverse.review.service.ReviewService;
import com.yts8.sixuniverse.room.dto.RoomDto;
import com.yts8.sixuniverse.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

  private final ReviewService reviewService;
  private final MemberService memberService;
  private final RoomService roomService;
  private final ReservationService reservationService;
  private final PerformanceService performanceService;

  @GetMapping("/guest-about")
  public String about(HttpSession session, Model model) {
    model.addAttribute("title", "후기");

    MemberDto member = (MemberDto) session.getAttribute("member");
    Long memberId = member.getMemberId();

    List<ReviewHostDto> reviewHostList = reviewService.reviewHostList(memberId);
    model.addAttribute("reviewHostList", reviewHostList);

    return "review/guest-review-about";
  }

  @GetMapping("/guest-by")
  public String by(HttpSession session, Model model) {
    model.addAttribute("title", "후기");

    MemberDto member = (MemberDto) session.getAttribute("member");

    List<ReviewGuestDto> reviewBeforeList = reviewService.reviewBefore(member.getMemberId());
    model.addAttribute("reviewBeforeList", reviewBeforeList);

    List<ReviewGuestDto> reviewAfterList = reviewService.reviewAfter(member.getMemberId());
    model.addAttribute("reviewAfterList", reviewAfterList);

    List<ReviewGuestDto> reviewGuestList = reviewService.reviewGuestList(member.getMemberId());
    model.addAttribute("reviewGuestList", reviewGuestList);

    LocalDate today = LocalDate.now();
    model.addAttribute("today", today);

    return "review/guest-review-by";
  }

  @GetMapping("/host-review")
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

  @PostMapping("/host-review/update")
  public String hostUpdate(ReviewDto reviewDto) {

    ReviewDto review = reviewService.getReview(reviewDto.getReviewId());

    LocalDate today = LocalDate.now();
    LocalDate reviewRegDate = review.getReviewRegDate();
    LocalDate reviewLimit = reviewRegDate.plusDays(2);
    Period period = Period.between(today, reviewLimit);

    if (period.getDays() < 0 || period.getDays() > 2) {
      return "redirect:/review/host-review";
    } else {
      reviewService.updateReply(reviewDto);
      return "redirect:/review/host-review";
    }

  }

  @PostMapping("/host-review/delete")
  public String hostDelete(ReviewDto reviewDto) {

    ReviewDto review = reviewService.getReview(reviewDto.getReviewId());

    LocalDate today = LocalDate.now();
    LocalDate reviewRegDate = review.getReviewRegDate();
    LocalDate reviewLimit = reviewRegDate.plusDays(2);
    Period period = Period.between(today, reviewLimit);

    if (period.getDays() < 0 || period.getDays() > 2) {
      return "redirect:/review/host-review";
    } else {
      reviewService.deleteReply(reviewDto);
      return "redirect:/review/host-review";
    }

  }

  @GetMapping("/form/{reservationId}")
  public String reviewForm(HttpSession session, @PathVariable Long reservationId, Model model) {
    model.addAttribute("title", "후기 작성");

    ReservationDto reservationDto = reservationService.findById(reservationId);

    ReviewDto reviewDto = reviewService.findByReservationId(reservationId);

    MemberDto member = (MemberDto) session.getAttribute("member");
    if (!member.getMemberId().equals(reservationDto.getMemberId()) || reviewDto != null) {
      return "redirect:/review/guest-by";
    }

    RoomDto roomDto = roomService.findById(reservationDto.getRoomId());
    model.addAttribute("roomDto", roomDto);

    model.addAttribute("memberDto", memberService.findById(roomDto.getMemberId()));

    return "review/review-form";
  }

  @GetMapping("/update-form/{reviewId}")
  public String reviewUpdateForm(Model model, @PathVariable Long reviewId) {
    model.addAttribute("title", "후기 수정");

    ReviewDto reviewDto = reviewService.findById(reviewId);

    Long roomId = reviewDto.getRoomId();
    RoomDto roomDto = roomService.findById(roomId);
    model.addAttribute("roomDto", roomDto);

    Long memberId = roomDto.getMemberId();
    MemberDto memberDto = memberService.findById(memberId);
    model.addAttribute("memberDto", memberDto);

    ReviewDto getReview = reviewService.getReview(reviewId);
    model.addAttribute("getReview", getReview);

    LocalDate today = LocalDate.now();
    LocalDate reviewRegDate = reviewDto.getReviewRegDate();
    LocalDate reviewLimit = reviewRegDate.plusDays(2);
    Period period = Period.between(today, reviewLimit);

    if (period.getDays() < 0 || period.getDays() > 2) {
      return "redirect:/review/guest-by";
    }

    return "review/review-update-form";
  }

  @PostMapping("/update-form")
  public String reviewUpdate(ReviewDto reviewDto) {
    reviewService.updateReview(reviewDto);

    return "review/review-complete";
  }

  @PostMapping("/guest-by/delete")
  public String reviewDelete(ReviewDto reviewDto) {

    ReviewDto review = reviewService.getReview(reviewDto.getReviewId());

    LocalDate today = LocalDate.now();
    LocalDate reviewRegDate = review.getReviewRegDate();
    LocalDate reviewLimit = reviewRegDate.plusDays(2);
    Period period = Period.between(today, reviewLimit);

    if (period.getDays() < 0 || period.getDays() > 2) {
      return "redirect:/review/guest-by";
    } else {
      reviewService.deleteReview(reviewDto);
      return "redirect:/review/guest-by";
    }

  }

  @PostMapping("/complete")
  public String reviewComplete(ReviewDto reviewDto) {
    reviewService.insertReview(reviewDto);

    return "review/review-complete";
  }

}
