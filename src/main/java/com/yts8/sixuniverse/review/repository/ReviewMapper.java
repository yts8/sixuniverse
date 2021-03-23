package com.yts8.sixuniverse.review.repository;

import com.yts8.sixuniverse.review.dto.ReviewDto;
import com.yts8.sixuniverse.review.dto.ReviewGuestDto;
import com.yts8.sixuniverse.review.dto.ReviewHostDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {

  void insertReview(ReviewDto reviewDto);

  ReviewDto findById(Long reviewId);

  List<ReviewGuestDto> reviewBefore(Long memberId);

  List<ReviewGuestDto> reviewAfter(Long memberId);

  List<ReviewGuestDto> reviewGuestList(Long memberId);

  List<ReviewHostDto> reviewHostList(Long memberId);

  List<ReviewHostDto> reviewReservationList(Long memberId);

  ReviewDto getReview(Long reviewId);

  void updateReview(ReviewDto reviewDto);

  void deleteReview(ReviewDto reviewDto);

  int reviewCount(Long memberId);

  double reviewScoreClean(Long memberId);

  double reviewScoreLocation(Long memberId);

  double reviewScoreService(Long memberId);

}
