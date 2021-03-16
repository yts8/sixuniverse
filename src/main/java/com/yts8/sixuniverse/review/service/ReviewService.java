package com.yts8.sixuniverse.review.service;

import com.yts8.sixuniverse.review.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

  void insertReview(ReviewDto reviewDto);

  ReviewDto findById(Long reviewId);

  List<ReviewDto> reviewList();

  ReviewDto getReview(Long reviewId);

  void updateReview(ReviewDto reviewDto);

  void deleteReview(ReviewDto reviewDto);

}
