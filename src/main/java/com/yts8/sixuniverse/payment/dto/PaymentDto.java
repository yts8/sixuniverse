package com.yts8.sixuniverse.payment.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDto {
  private Long paymentId;
  private Long reservationId;
  private Long memberCouponId;
  private int price;
  private int commission;
  private int mileage;
  private int discount;
  private String paymentMethod;
  private LocalDateTime createDate;
  private LocalDateTime cancelDate;
}
