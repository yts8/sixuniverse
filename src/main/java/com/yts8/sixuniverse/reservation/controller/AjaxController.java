package com.yts8.sixuniverse.reservation.controller;

import com.yts8.sixuniverse.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class AjaxController {

  private final ReservationService reservationService;

  @GetMapping("/guest/update/ajax")
  public boolean updateDate(HttpServletRequest request) {
//    ResponseEntity<String> entity = null;
    boolean result = false;

    String checkIn = request.getParameter("checkIn");
    String today = LocalDate.now() + "";

    System.out.println("checkIn : " + checkIn);
    System.out.println("today : " + today);

    if(checkIn.equals(today)) {
      result = true;
    }

//    entity = new ResponseEntity<>(result, HttpStatus.OK);

    return result;
  }

}
