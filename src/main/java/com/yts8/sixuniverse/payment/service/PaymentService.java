package com.yts8.sixuniverse.payment.service;

import com.yts8.sixuniverse.payment.dto.PaymentDto;

public interface PaymentService {
  void paymentInsert(PaymentDto paymentDto);
}