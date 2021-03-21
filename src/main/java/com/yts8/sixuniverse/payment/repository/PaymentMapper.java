package com.yts8.sixuniverse.payment.repository;

import com.yts8.sixuniverse.payment.dto.PaymentDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {
  void paymentInsert(PaymentDto paymentDto);

}
