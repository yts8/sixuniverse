package com.yts8.sixuniverse.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class reservationController {

  @GetMapping("/reservation")
  public String reservation() {


    return "reservation/reservation";
  }

}
