package com.yts8.sixuniverse.review.repository;

import com.yts8.sixuniverse.review.dto.ReviewDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {

  void insertReview(ReviewDto reviewDto);

  ReviewDto findById(Long reviewId);

  List<ReviewDto> reviewList();

  ReviewDto getReview(Long reviewId);

  void updateReview(ReviewDto reviewDto);

  void deleteReview(ReviewDto reviewDto);

}
