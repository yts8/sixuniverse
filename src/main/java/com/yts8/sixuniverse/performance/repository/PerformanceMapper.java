package com.yts8.sixuniverse.performance.repository;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PerformanceMapper {

  void updateSuperHost(Long memberId);

  double findByReviewScore(Long memberId);

  int findByRoomCount(Long memberId);

  int findByHits(Long memberId);

  int findByHitsList(Long memberId, int interval);

  String findBySuperHostIs(Long memberId);

  double findByCancelCount(Long memberId);

  int findByReservationCount(Long memberId);

  int findByReservationMonthlyCount(Long memberId);

  int findByMonthlyIncome(Long memberId);

  int findByMonthlyIncomeCount(Long memberId);

  int findByYearlyIncome(Long memberId);

  int findByYearlyIncomeCount(Long memberId);

  int monthlyIncomeList(Long memberId, int interval);

}
