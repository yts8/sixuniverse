package com.yts8.sixuniverse.performance.service;

public interface PerformanceService {

  double findByReviewScore(Long memberId);

  int findByRoomCount(Long memberId);

  double findByCancelRatio(Long memberId);

  int findByReservationCount(Long memberId);

  int findByReservationMonthlyCount(Long memberId);

  int findByMonthlyIncome(Long memberId);

  int findByMonthlyIncomeCount(Long memberId);

  int findByYearlyIncome(Long memberId);

  int findByYearlyIncomeCount(Long memberId);
}
