package com.yts8.sixuniverse.payment.service;

import com.yts8.sixuniverse.payment.dto.PaymentDto;
import com.yts8.sixuniverse.payment.repository.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

  private final PaymentMapper paymentMapper;

  @Override
  public void paymentInsert(PaymentDto paymentDto) {
    paymentMapper.paymentInsert(paymentDto);
  }

  @Override
  public PaymentDto findByReservationId(Long reservationId) {
    return paymentMapper.findByReservationId(reservationId);
  }

  @Override
  public void paymentCancel(PaymentDto paymentDto) {
    paymentMapper.paymentCancel(paymentDto);
  }

  @Override
  public void paymentUpdate(PaymentDto paymentDto) {
    paymentMapper.paymentUpdate(paymentDto);
  }
}
