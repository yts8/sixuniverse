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
  public String about() {

    return "review/guest-review-about";
  }

  @GetMapping("/guest-by")
  public String by(HttpSession session, Model model) {

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

  @GetMapping("/host")
  public String host(HttpSession session, Model model) {

    MemberDto member = (MemberDto) session.getAttribute("member");
    Long memberId = member.getMemberId();

    List<ReviewHostDto> reviewHostList = reviewService.reviewHostList(memberId);
    model.addAttribute("reviewHostList", reviewHostList);

    NumberFormat formatter = new DecimalFormat("0.#");
    double reviewScore = performanceService.findByReviewScore(member.getMemberId());
    model.addAttribute("reviewScore",  formatter.format(reviewScore));

    int reviewCount = reviewService.reviewCount(member.getMemberId());
    model.addAttribute("reviewCount", reviewCount);

    return "review/host-review";
  }

  @GetMapping("/reservation/{roomId}")
  public String reviewReservation(@PathVariable Long roomId, Model model) {

    RoomDto roomDto = roomService.findById(roomId);

    List<ReviewHostDto> reviewHostList = reviewService.reviewHostList(roomDto.getMemberId());
    model.addAttribute("reviewHostList", reviewHostList);

    List<ReviewHostDto> reviewReservationList = reviewService.reviewReservationList(roomDto.getMemberId());
    model.addAttribute("reviewReservationList", reviewReservationList);

    NumberFormat formatter = new DecimalFormat("0.#");
    double reviewScore = performanceService.findByReviewScore(roomDto.getMemberId());
    model.addAttribute("reviewScore",  formatter.format(reviewScore));

    double reviewScoreClean = reviewService.reviewScoreClean(roomDto.getMemberId());
    model.addAttribute("reviewScoreClean",  formatter.format(reviewScoreClean));

    double reviewScoreLocation = reviewService.reviewScoreLocation(roomDto.getMemberId());
    model.addAttribute("reviewScoreLocation",  formatter.format(reviewScoreLocation));

    double reviewScoreService = reviewService.reviewScoreService(roomDto.getMemberId());
    model.addAttribute("reviewScoreService",  formatter.format(reviewScoreService));

    int reviewCount = reviewService.reviewCount(roomDto.getMemberId());
    model.addAttribute("reviewCount", reviewCount);

    return "review/reservation-review";
  }

  @GetMapping("/form/{reservationId}")
  public String reviewForm(@PathVariable Long reservationId, Model model) {

//    reservationId -> review 테이블 찾아 있어? 그럼 redirect:/ 없으면 써.
//    ReviewDto reviewDto = reviewService.findById(reservationId);
//    if (reviewDto != null) {
//      return "redirect:/review/guest-by";
//    }

    ReservationDto reservationDto = reservationService.findById(reservationId);

    Long roomId = reservationDto.getRoomId();
    RoomDto roomDto = roomService.findById(roomId);
    model.addAttribute("roomDto", roomDto);

    Long memberId = roomDto.getMemberId();
    MemberDto memberDto = memberService.findById(memberId);
    model.addAttribute("memberDto", memberDto);

    return "review/review-form";
  }

  @GetMapping("/update-form/{reviewId}")
  public String reviewUpdateForm(Model model, @PathVariable Long reviewId) {

    ReviewDto reviewDto = reviewService.findById(reviewId);

    Long roomId = reviewDto.getRoomId();
    RoomDto roomDto = roomService.findById(roomId);
    model.addAttribute("roomDto", roomDto);

    Long memberId = roomDto.getMemberId();
    MemberDto memberDto = memberService.findById(memberId);
    model.addAttribute("memberDto", memberDto);

    ReviewDto getReview = reviewService.getReview(reviewId);
    model.addAttribute("getReview", getReview);

    return "review/review-update-form";
  }

  @PostMapping("/update-form")
  public String reviewUpdate(ReviewDto reviewDto) {
    reviewService.updateReview(reviewDto);

    return "review/review-complete";
  }

  @PostMapping("/guest-by/delete")
  public String reviewDelete(ReviewDto reviewDto) {
    reviewService.deleteReview(reviewDto);

    return "redirect:/review/guest-by";
  }

  @PostMapping("/complete")
  public String reviewComplete(ReviewDto reviewDto) {
    reviewService.insertReview(reviewDto);

    return "review/review-complete";
  }

}
