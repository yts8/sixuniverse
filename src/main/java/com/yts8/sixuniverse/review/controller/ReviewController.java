package com.yts8.sixuniverse.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/review")
public class ReviewController {

  @GetMapping("/about")
  public String about() {
    return "review/guest-review-about";
  }

  @GetMapping("/by")
  public String by() {
    return "review/guest-review-by";
  }

}
