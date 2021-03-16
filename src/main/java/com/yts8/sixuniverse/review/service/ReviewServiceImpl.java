package com.yts8.sixuniverse.review.service;

import com.yts8.sixuniverse.review.dto.ReviewDto;
import com.yts8.sixuniverse.review.repository.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

  private final ReviewMapper reviewMapper;

  @Override
  public void insertReview(ReviewDto reviewDto) { reviewMapper.insertReview(reviewDto); }

  @Override
  public ReviewDto findById(Long reviewId) {
    return reviewMapper.findById(reviewId);
  }

  @Override
  public List<ReviewDto> reviewList() {
    return reviewMapper.reviewList();
  }

  @Override
  public ReviewDto getReview(Long reviewId) {
    return reviewMapper.getReview(reviewId);
  }

  @Override
  public void updateReview(ReviewDto reviewDto) {
    reviewMapper.updateReview(reviewDto);
  }

  @Override
  public void deleteReview(ReviewDto reviewDto) {
    reviewMapper.deleteReview(reviewDto);
  }

}
