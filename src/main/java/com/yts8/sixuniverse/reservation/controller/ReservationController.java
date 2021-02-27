package com.yts8.sixuniverse.reservation.controller;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping("/guest-reservation")
  public String guestReservation(HttpServletRequest request, Model model) {

    String status = request.getParameter("status");

      if(status==null || status.equals("upcoming")) {
        List<String> testList = new ArrayList<String>();
        testList.add("숙소1");
        testList.add("숙소2");
        testList.add("숙소3");
        testList.add("숙소4");

        model.addAttribute("testList", testList);

      } else {
        List<String> testList = new ArrayList<String>();
        testList.add("완료숙소1");
        testList.add("완료숙소2");
        testList.add("완료숙소3");
        testList.add("완료숙소4");

        model.addAttribute("testList", testList);
      }

    return "reservation/guest-reservation-list";
  }

  @GetMapping("/guest-reservation-detail")
  public String detail(HttpServletRequest request) {

    String status = request.getParameter("status");

    System.out.println(status);

    return "reservation/guest-reservation-detail";
  }

}
