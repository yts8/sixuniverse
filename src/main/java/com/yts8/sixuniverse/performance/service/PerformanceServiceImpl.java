package com.yts8.sixuniverse.performance.service;


import com.yts8.sixuniverse.performance.repository.PerformanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PerformanceServiceImpl implements PerformanceService {

  private final PerformanceMapper performanceMapper;

  @Override
  public double findByReviewScore(Long memberId) {
    return performanceMapper.findByReviewScore(memberId);
  }


}
