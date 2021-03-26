package com.yts8.sixuniverse.performance.service;


import com.yts8.sixuniverse.performance.repository.PerformanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PerformanceServiceImpl implements PerformanceService {

  private final PerformanceMapper performanceMapper;

  @Override
  public void updateSuperHost(Long memberId) {
    performanceMapper.updateSuperHost(memberId);
  }

  @Override
  public void updateSuperHostScheduler() {
  }

  @Override
  public double findByReviewScore(Long memberId) {
    return performanceMapper.findByReviewScore(memberId);
  }

  @Override
  public int findByRoomCount(Long memberId) {

    return performanceMapper.findByRoomCount(memberId);
  }

  @Override
  public int findByHits(Long memberId) {
    return performanceMapper.findByHits(memberId);
  }

  @Override
  public int findByHitsList(Long memberId, int interval) {
    return performanceMapper.findByHitsList(memberId, interval);
  }

  @Override
  public String findBySuperHostIs(Long memberId) {
    return performanceMapper.findBySuperHostIs(memberId);
  }

  @Override
  public double findByCancelRatio(Long memberId) {
    return performanceMapper.findByCancelCount(memberId);
  }

  @Override
  public int findByReservationCount(Long memberId) {
    return performanceMapper.findByReservationCount(memberId);
  }

  @Override
  public int findByReservationMonthlyCount(Long memberId) {

    return performanceMapper.findByReservationMonthlyCount(memberId);
  }

  @Override
  public int findByMonthlyIncome(Long memberId) {

    return performanceMapper.findByMonthlyIncome(memberId);
  }

  @Override
  public int findByMonthlyIncomeCount(Long memberId) {

    return performanceMapper.findByMonthlyIncomeCount(memberId);
  }

  @Override
  public int findByYearlyIncome(Long memberId) {

    return performanceMapper.findByYearlyIncome(memberId);
  }

  @Override
  public int findByYearlyIncomeCount(Long memberId) {

    return performanceMapper.findByYearlyIncomeCount(memberId);
  }

  @Override
  public int monthlyIncomeList(Long memberId, int interval) {

    return performanceMapper.monthlyIncomeList(memberId, interval);
  }


}
