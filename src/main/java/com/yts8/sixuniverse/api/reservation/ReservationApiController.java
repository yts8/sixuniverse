package com.yts8.sixuniverse.api.reservation;

import com.amazonaws.services.ec2.model.ResetSnapshotAttributeRequest;
import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.payment.dto.PaymentDto;
import com.yts8.sixuniverse.payment.repository.PaymentMapper;
import com.yts8.sixuniverse.payment.service.PaymentService;
import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import com.yts8.sixuniverse.reservation.service.ReservationService;
import com.yts8.sixuniverse.reservationDate.service.ReservationDateService;
import com.yts8.sixuniverse.room.dto.RoomDto;
import com.yts8.sixuniverse.room.service.RoomService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationApiController {

  private final RoomService roomService;
  private final ReservationService reservationService;
  private final ReservationDateService reservationDateService;
  private final PaymentService paymentService;

  @PostMapping("/guest/update/today")
  public @ResponseBody
  boolean updateDate(@RequestBody LocalDate checkIn) {
    boolean result = false;

    LocalDate today = LocalDate.now();

    if (checkIn.equals(today)) {
      result = true;
    }

    return result;
  }


  @PostMapping("/guest/update/complete")
  public @ResponseBody void guestReservationUpdateComplete(@RequestBody ReservationDto reservationDto) {
    Long reservationId = reservationDto.getReservationId();
    reservationService.guestReservationUpdateRequest(reservationId);

    ReservationDto originalReservationDto = reservationService.findById(reservationId);
    reservationDto.setRoomId(originalReservationDto.getRoomId());
    reservationDto.setStatus("update");
    reservationDto.setMemberId(originalReservationDto.getMemberId());

    reservationService.guestReservationUpdateInsert(reservationDto);

  }

  @PostMapping("/guest/cancel")
  public @ResponseBody void cancel(@RequestBody Long reservationId) {
    ReservationDto reservationDto = new ReservationDto();
    reservationDto.setReservationId(reservationId);
    reservationDto.setStatus("cancel");
    reservationDto.setCancelDate(LocalDateTime.now());

    reservationService.guestReservationCancel(reservationDto);
    reservationDateService.guestReservationDateDelete(reservationId);
  }

  @GetMapping("/update/info{reservationId}")
  public ReservationDto listUpdateInfo(@PathVariable Long reservationId) {

    return reservationService.findByUpdateTarget(reservationId);
  }

  @GetMapping("/cancel/info/{reservationId}")
  public ReservationDto listCancelInfo(@PathVariable Long reservationId) {

    return reservationService.findById(reservationId);
  }

  @PostMapping("/before")
  public @ResponseBody int reservationCheck(@RequestBody ReservationDto reservationDto) {
    LocalDate checkIn = reservationDto.getCheckIn();
    LocalDate checkOut = reservationDto.getCheckOut();

    int days = Period.between(checkIn, checkOut).getDays();
    RoomDto roomDto = roomService.findById(reservationDto.getRoomId());

    int oneDayPrice = roomDto.getPrice();

    int totalPrice = oneDayPrice * days;

    return totalPrice;

  }


}


