package com.yts8.sixuniverse.review.controller;

import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.member.service.MemberService;
import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import com.yts8.sixuniverse.reservation.service.ReservationService;
import com.yts8.sixuniverse.review.dto.ReviewDto;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

  private final ReviewService reviewService;
  private final MemberService memberService;
  private final RoomService roomService;
  private final ReservationService reservationService;

  @GetMapping("/guest-about")
  public String about() {

    return "review/guest-review-about";
  }

  @GetMapping("/guest-by")
  public String by(HttpSession session, Model model) {

    MemberDto memberDto = (MemberDto) session.getAttribute("member");
    Long memberId = memberDto.getMemberId();

    ReservationDto reservationDto = new ReservationDto();
    reservationDto.setMemberId(memberId);
    reservationDto.setStatus("complete");
    reservationDto.setCancelDate(null);
    List<ReservationDto> reservationList = reservationService.reservationList(reservationDto);
    model.addAttribute("reservationList", reservationList);

    List<RoomDto> roomList = new ArrayList<>();
    for(ReservationDto reservationDto1 : reservationList) {
      Long roomId = reservationDto1.getRoomId();
      RoomDto roomDto = roomService.findById(roomId);
      roomList.add(roomDto);
    }
    model.addAttribute("roomList", roomList);

    List<ReviewDto> reviewList = reviewService.reviewList();
    model.addAttribute("reviewList", reviewList);

    LocalDate today = LocalDate.now();

    // checkout 날짜에서 하루 지난 날짜 = today 일 때 작성해야할 후기
    // today <= threeDaysAfter    => 작성해야할 후기
    // today > threeDaysAfter     => 만료된 후기
    for(ReservationDto reviewWrite : reservationList) {

      LocalDate checkout = reviewWrite.getCheckOut();
      LocalDate oneDaysAfter = checkout.plusDays(1);
      LocalDate threeDaysAfter = oneDaysAfter.plusDays(3);

      model.addAttribute("today", today);
      model.addAttribute("reviewStart", oneDaysAfter);
      model.addAttribute("reviewEnd", threeDaysAfter);

    }

    // 후기 작성일부터 3일 안에만 수정, 삭제 가능
    for(ReviewDto review : reviewList) {

      LocalDate reviewRegDate = review.getReviewRegDate();
      LocalDate reviewLimit = reviewRegDate.plusDays(3);

      model.addAttribute("reviewLimit", reviewLimit);

    }

    return "review/guest-review-by";
  }

  @GetMapping("/host")
  public String host() {


    return "review/host-review";
  }

  @GetMapping("/reservation")
  public String reviewReservation() {

    return "review/reservation-review";
  }

  @GetMapping("/form/{reservationId}")
  public String reviewForm(@PathVariable Long reservationId, Model model) {

    ReservationDto reservationDto = reservationService.findById(reservationId);
    Long roomId = reservationDto.getRoomId();
    RoomDto roomDto = roomService.findById(roomId);
    model.addAttribute("roomDto", roomDto);

    return "review/review-form";
  }

  @GetMapping("/update-form/{reviewId}")
  public String reviewUpdateForm(Model model, @PathVariable Long reviewId) {

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
