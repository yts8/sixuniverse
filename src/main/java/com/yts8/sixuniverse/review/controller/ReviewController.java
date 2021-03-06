package com.yts8.sixuniverse.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/review")
public class ReviewController {

  @GetMapping("/guest-about")
  public String about() {
    return "review/guest-review-about";
  }

  @GetMapping("/guest-by")
  public String by() {
    return "review/guest-review-by";
  }

  @GetMapping("/host")
  public String host() { return "review/host-review"; }

  @GetMapping("/form")
  public String reviewForm() {
    return "review/review-form";
  }

  @GetMapping("/reservation")
  public String reviewReservation() {
    return "review/reservation-review";
  }


}
