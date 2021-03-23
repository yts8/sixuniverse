package com.yts8.sixuniverse.reservation.scheduler.reservation;

import com.yts8.sixuniverse.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class CompleteReservation {

  private final ReservationService reservationService;

  @Scheduled(cron = "0 0 0 * * *")
  public void cronReservation() {
    LocalDate today = LocalDate.now();

    reservationService.reservationCheckOut(today);

    System.out.println(today);
  }

}
