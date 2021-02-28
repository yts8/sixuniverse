package com.yts8.sixuniverse.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

  @GetMapping("")
  public String reservation() {

    return "reservation/index";
  }

  @GetMapping("/host-reservation-list")
  public String reservationHost() {

    return "reservation/host-reservation-list";
  }


}
