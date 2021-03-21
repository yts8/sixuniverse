package com.yts8.sixuniverse.api.reservation;

import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.yts8.sixuniverse.payment.dto.PaymentDto;
import com.yts8.sixuniverse.payment.service.PaymentService;
import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import com.yts8.sixuniverse.reservation.dto.ReservationRoomPaymentDto;
import com.yts8.sixuniverse.reservation.service.IamportClient;
import com.yts8.sixuniverse.reservation.service.ReservationService;
import com.yts8.sixuniverse.reservationDate.service.ReservationDateService;
import com.yts8.sixuniverse.room.dto.RoomDto;
import com.yts8.sixuniverse.room.service.RoomService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationApiController {

  private final RoomService roomService;
  private final ReservationService reservationService;
  private final ReservationDateService reservationDateService;
  private final PaymentService paymentService;

  @PostMapping("/guest/update/complete")
  public void guestReservationUpdateComplete(@RequestBody ReservationDto reservationDto) {
    Long reservationId = reservationDto.getReservationId();
    reservationService.guestReservationUpdateRequest(reservationId);

    ReservationDto originalReservationDto = reservationService.findById(reservationId);
    reservationDto.setRoomId(originalReservationDto.getRoomId());
    reservationDto.setStatus("update");
    reservationDto.setMemberId(originalReservationDto.getMemberId());

    reservationService.guestReservationUpdateInsert(reservationDto);

  }

  @GetMapping("/update/info/{reservationId}")
  public List<ReservationRoomPaymentDto> listUpdateInfo(@PathVariable Long reservationId) {

    return reservationService.findByUpdateReservationId(reservationId);
  }

  @GetMapping("/cancel/info/{reservationId}")
  public ReservationRoomPaymentDto listCancelInfo(@PathVariable Long reservationId) {

    return reservationService.findByCancelReservationId(reservationId);
  }

  @PostMapping("/before")
  public int reservationCheck(@RequestBody ReservationDto reservationDto) {
    LocalDate checkIn = reservationDto.getCheckIn();
    LocalDate checkOut = reservationDto.getCheckOut();

    int days = Period.between(checkIn, checkOut).getDays();
    RoomDto roomDto = roomService.findById(reservationDto.getRoomId());

    int oneDayPrice = roomDto.getPrice();

    int totalPrice = oneDayPrice * days;

    return totalPrice;

  }

  @PostMapping("/guest/pay/cancel")
  public void reservationPayCancel(@RequestBody Map<String, String> json) throws IOException, IamportResponseException {
    String apiKey = "2408991764225801"; // 아임포트 키
    String apiSecret = "smb4OZF2sLSpFZ9nomAWdF6PDjwwhd8JzjOojtqMejbUikhHHSDVxKKa3hGluP549TttmUfBdqvvLNct";
    IamportClient iamportClient = new IamportClient(apiKey, apiSecret);

    CancelData cancelData = new CancelData(json.get("imp_uid"), true);
    iamportClient.cancelPaymentByImpUid(cancelData);
  }


  @PostMapping("/guest/cancel")
  public void cancel(@RequestBody Map<String, String> json) {
    Long reservationId = Long.parseLong(json.get("reservationId"));

    ReservationDto reservationDto = new ReservationDto();
    reservationDto.setReservationId(reservationId);
    reservationDto.setStatus("cancel");
    reservationDto.setCancelDate(LocalDateTime.now());

    reservationService.guestReservationCancel(reservationDto);
    reservationDateService.guestReservationDateDelete(reservationId);

    PaymentDto paymentDto = new PaymentDto();
    paymentDto.setPaymentId(json.get("paymentId"));
    paymentDto.setCancelDate(LocalDateTime.now());
    paymentService.paymentCancel(paymentDto);
  }

}


