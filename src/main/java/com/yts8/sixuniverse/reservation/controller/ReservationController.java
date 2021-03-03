package com.yts8.sixuniverse.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

  @GetMapping("")
  public String reservation() {

    return "reservation/index";
  }

  @GetMapping("/guest-reservation-list")
  public String guestReservation(HttpServletRequest request, Model model) {

    String status = request.getParameter("status");

      if(status==null || status.equals("upcoming")) {
        List<String> testList = new ArrayList<String>();
        testList.add("upcoming");
        testList.add("upcoming");
        testList.add("upcoming");
        testList.add("upcoming");

        model.addAttribute("testList", testList);

      } else {
        List<String> testList = new ArrayList<String>();
        testList.add("complete");
        testList.add("complete");
        testList.add("complete");
        testList.add("complete");
        testList.add("complete");
        testList.add("complete");

        model.addAttribute("testList", testList);
      }

    return "reservation/guest-reservation-list";
  }

  @GetMapping("/guest-reservation-simple-info")
  public String simple(HttpServletRequest request, Model model) {

    String status = request.getParameter("status");

    model.addAttribute("status", status);

    return "reservation/guest-reservation-simple-info";
  }


  @GetMapping("/guest-reservation-detail-info")
  public String detail(HttpServletRequest request, Model model) {

    String status = request.getParameter("status");

    model.addAttribute("status", status);

    return "reservation/guest-reservation-detail-info";
  }

  @GetMapping("/host-reservation-list")
  public String reservationHost() {

    return "reservation/host-reservation-list";
  }

  @GetMapping("/update")
  public String guestReservationUpdate() {

    return "reservation/guest-reservation-update";
  }

  @PostMapping("/update")
  public String guestReservationUpdateResult() {

    return "reservation/guest-reservation-update-result";
  }


  @GetMapping("/complete")
  public String guestReservationUpdateComplete() {

    return "reservation/guest-reservation-update-result";
  }

  @GetMapping("/cancel")
  public String guestReservationCancel() {

    return "reservation/guest-reservation-cancel";
  }

  @GetMapping("/test")
  public String test() {

    return "reservation/test";
  }


}
