package com.yts8.sixuniverse.performance.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PerformanceMapper {

  double findByReviewScore(Long memberId);

  int findByRoomCount(Long memberId);

  double findByCancelCount(Long memberId);

  int findByReservationCount(Long memberId);

  int findByReservationMonthlyCount(Long memberId);

  int findByMonthlyIncome(Long memberId);

  int findByMonthlyIncomeCount(Long memberId);

  int findByYearlyIncome(Long memberId);

  int findByYearlyIncomeCount(Long memberId);

}
