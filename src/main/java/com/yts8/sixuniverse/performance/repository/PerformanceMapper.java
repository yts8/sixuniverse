package com.yts8.sixuniverse.performance.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PerformanceMapper {

  double findByReviewScore(Long memberId);

}
