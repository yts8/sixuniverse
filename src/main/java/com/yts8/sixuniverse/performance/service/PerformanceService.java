package com.yts8.sixuniverse.performance.service;

import java.util.List;

public interface PerformanceService {
  void updateSuperHost(Long memberId);

  double findByReviewScore(Long memberId);

  int findByRoomCount(Long memberId);

  int findByHits(Long memberId);

  int findByHitsList(Long memberId, int interval);

  String findBySuperHostIs(Long memberId);

  double findByCancelRatio(Long memberId);

  int findByReservationCount(Long memberId);

  int findByReservationMonthlyCount(Long memberId);

  int findByMonthlyIncome(Long memberId);

  int findByMonthlyIncomeCount(Long memberId);

  int findByYearlyIncome(Long memberId);

  int findByYearlyIncomeCount(Long memberId);

  int monthlyIncomeList(Long memberId, int interval);
}
