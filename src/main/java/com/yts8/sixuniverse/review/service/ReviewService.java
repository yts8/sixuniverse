package com.yts8.sixuniverse.review.service;

import com.yts8.sixuniverse.review.dto.ReviewDto;
import com.yts8.sixuniverse.review.dto.ReviewGuestDto;
import com.yts8.sixuniverse.review.dto.ReviewHostDto;

import java.util.List;

public interface ReviewService {

  void insertReview(ReviewDto reviewDto);

  ReviewDto findById(Long reviewId);

  ReviewDto findByReservationId(Long reservationId);

  List<ReviewGuestDto> reviewBefore(Long memberId);

  List<ReviewGuestDto> reviewAfter(Long memberId);

  List<ReviewGuestDto> reviewGuestList(Long memberId);

  List<ReviewHostDto> reviewHostList(Long memberId);

  List<ReviewHostDto> reviewReservationListAll(Long roomId);

  List<ReviewHostDto> reviewReservationList(Long memberId);

  ReviewDto getReview(Long reviewId);

  void updateReview(ReviewDto reviewDto);

  void deleteReview(ReviewDto reviewDto);

  void updateReply(ReviewDto reviewDto);

  void deleteReply(ReviewDto reviewDto);

  int reviewCount(Long memberId);

  double reviewScoreClean(Long memberId);

  double reviewScoreLocation(Long memberId);

  double reviewScoreService(Long memberId);
}
