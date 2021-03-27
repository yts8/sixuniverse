package com.yts8.sixuniverse.api.reservation;

import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.yts8.sixuniverse.payment.dto.PaymentDto;
import com.yts8.sixuniverse.payment.service.PaymentService;
import com.yts8.sixuniverse.reservation.dto.ReservationDto;
import com.yts8.sixuniverse.reservation.dto.ReservationRoomPaymentDto;
import com.yts8.sixuniverse.reservation.service.IamportClient;
import com.yts8.sixuniverse.reservation.service.ReservationService;
import com.yts8.sixuniverse.reservationDate.dto.ReservationDateDto;
import com.yts8.sixuniverse.reservationDate.service.ReservationDateService;
import com.yts8.sixuniverse.room.dto.RoomDto;
import com.yts8.sixuniverse.room.service.RoomService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
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
    System.out.println(reservationDto);

    Long reservationId = reservationDto.getReservationId();
    reservationService.guestReservationUpdateRequest(reservationId);

    ReservationDto originalReservationDto = reservationService.findById(reservationId);
    reservationDto.setRoomId(originalReservationDto.getRoomId());
    reservationDto.setStatus("update");
    reservationDto.setMemberId(originalReservationDto.getMemberId());

    reservationService.guestReservationUpdateInsert(reservationDto);

  }

  @PostMapping("/update/check")
  public boolean updateCheck(@RequestBody ReservationDto reservationDto) {
    boolean result = true;

    ReservationDto originalReservationDto = reservationService.findById(reservationDto.getReservationId());
    int originalAdult = originalReservationDto.getAdult();
    int originalKid = originalReservationDto.getKid();
    int originalInfant = originalReservationDto.getInfant();

    LocalDate originalCheckIn = originalReservationDto.getCheckIn();
    LocalDate originalCheckOut = originalReservationDto.getCheckOut();

    int adult = reservationDto.getAdult();
    int kid = reservationDto.getKid();
    int infant = reservationDto.getInfant();

    LocalDate checkIn = reservationDto.getCheckIn();
    LocalDate checkOut = reservationDto.getCheckOut();

    if (originalAdult == adult && originalKid == kid && originalInfant == infant
        && originalCheckIn.equals(checkIn) && originalCheckOut.equals(checkOut)) {
      result = false;
    }


    return result;
  }

  @GetMapping("/update/info/{reservationId}")
  public List<ReservationRoomPaymentDto> listUpdateInfo(@PathVariable Long reservationId) {
    List<ReservationRoomPaymentDto> reservationRoomPaymentDtos = reservationService.findByUpdateReservationId(reservationId);

    LocalDate checkIn = reservationRoomPaymentDtos.get(1).getCheckIn();
    LocalDate checkOut = reservationRoomPaymentDtos.get(1).getCheckOut();

    RoomDto roomDto = roomService.findById(reservationRoomPaymentDtos.get(1).getRoomId());

    int days = Period.between(checkIn, checkOut).getDays();
    int price = roomDto.getPrice();

    int totalPrice = price * days;
    int commission = (int) (totalPrice * 0.1);

    ReservationRoomPaymentDto reservationRoomPaymentDto = new ReservationRoomPaymentDto();
    if (reservationRoomPaymentDtos.get(0).getPrice() < totalPrice + commission) {
      reservationRoomPaymentDto.setPrice(totalPrice);
      reservationRoomPaymentDto.setCommission(commission);
    } else if (reservationRoomPaymentDtos.get(0).getPrice() > totalPrice + commission) {
      reservationRoomPaymentDto.setPrice(totalPrice);
      reservationRoomPaymentDto.setCommission(commission);
    }

    reservationRoomPaymentDtos.add(reservationRoomPaymentDto);

    return reservationRoomPaymentDtos;
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

  @PostMapping("/pay/cancel/again")
  public void cancelAgain(@RequestBody String paymentId) {
    PaymentDto paymentDto = new PaymentDto();
    paymentDto.setPaymentId(paymentId);
    paymentDto.setCancelDate(LocalDateTime.now());
    paymentService.paymentCancel(paymentDto);
  }

  @PostMapping("/host/update/update-ok")
  public String updateOk(@RequestBody ReservationDto reservationDto) {
    reservationService.hostUpdate(reservationDto);
    return "ok";
  }

  @PostMapping("/host/update/update-no")
  public String updateNo(@RequestBody ReservationDto reservationDto) {
    reservationService.hostUpdateNo(reservationDto.getReservationId());
    return "no";
  }

  @PostMapping("/pay/again")
  public String payAgain(@RequestBody PaymentDto paymentDto) {

    System.out.println("어게인");
    System.out.println("ReservationId : " + paymentDto.getReservationId());
    Long reservationId = paymentDto.getReservationId();

    ReservationDto reservationDto = reservationService.findByUpdateTarget(reservationId);
    reservationDto.setReservationId(reservationId);
    System.out.println("reservationDto : " + reservationDto);

    reservationDateService.guestReservationDateDelete(reservationId); // 변경 전 날짜 삭제

    PaymentDto paymentCancel = new PaymentDto();
    paymentDto.setPaymentId(paymentDto.getPaymentId());
    paymentDto.setCancelDate(LocalDateTime.now());
    paymentService.paymentCancel(paymentCancel);  // 변경 전 결제 정보 취소날짜 추가

    List<ReservationDateDto> reservationDateDtos = new ArrayList<ReservationDateDto>();

    LocalDate checkIn = reservationDto.getCheckIn();

    int days = Period.between(checkIn, reservationDto.getCheckOut()).getDays();
    for (int i = 0; i <= days; i++) {
      ReservationDateDto reservationDateDto = new ReservationDateDto();

      reservationDateDto.setRoomId(reservationDto.getRoomId());
      reservationDateDto.setReservationId(reservationId);
      reservationDateDto.setReservationDate(checkIn.plusDays(i));
      reservationDateDtos.add(reservationDateDto);
    }

    System.out.println("reservationDateDtos : " + reservationDateDtos);

    paymentService.paymentInsert(paymentDto);  // 재결제 정보
    reservationService.guestReservationUpdate(reservationDto); // 변경 후 정보로 업데이트
    reservationDateService.reservationDateInsert(reservationDateDtos); // 변경 후 날짜 추가

//    reservationService.reservationDelete(reservationDto.getReservationId()); // 변경 후 정보 삭제

    return "redirect:/reservation/guest/list";
  }

  @PostMapping("/pay/partial/refund")
  public void payPartialRefund(@RequestBody PaymentDto paymentDto) throws IOException, IamportResponseException {
    String apiKey = "2408991764225801"; // 아임포트 키
    String apiSecret = "smb4OZF2sLSpFZ9nomAWdF6PDjwwhd8JzjOojtqMejbUikhHHSDVxKKa3hGluP549TttmUfBdqvvLNct";
    IamportClient iamportClient = new IamportClient(apiKey, apiSecret);

    CancelData cancelData = new CancelData(paymentDto.getPaymentId(), true, BigDecimal.valueOf(paymentDto.getPrice()));
    iamportClient.cancelPaymentByImpUid(cancelData);
  }

  @PostMapping("/partial/refund")
  public void partialRefund(@RequestBody PaymentDto paymentDto) {
    System.out.println(paymentDto);

    Long reservationId = paymentDto.getReservationId();

    ReservationDto reservationDto = reservationService.findByUpdateTarget(reservationId);
    reservationDto.setReservationId(reservationId);

    reservationDateService.guestReservationDateDelete(reservationId); // 변경 전 날짜 삭제

    List<ReservationDateDto> reservationDateDtos = new ArrayList<ReservationDateDto>();

    LocalDate checkIn = reservationDto.getCheckIn();

    int days = Period.between(checkIn, reservationDto.getCheckOut()).getDays();
    for (int i = 0; i <= days; i++) {
      ReservationDateDto reservationDateDto = new ReservationDateDto();

      reservationDateDto.setRoomId(reservationDto.getRoomId());
      reservationDateDto.setReservationId(reservationId);
      reservationDateDto.setReservationDate(checkIn.plusDays(i));
      reservationDateDtos.add(reservationDateDto);
    }

    paymentService.paymentUpdate(paymentDto);
    reservationService.guestReservationUpdate(reservationDto); // 변경 후 정보로 업데이트
    reservationDateService.reservationDateInsert(reservationDateDtos); // 변경 후 날짜 추가

//    reservationService.reservationDelete(reservationDto.getReservationId()); // 변경 후 정보 삭제

  }

}


