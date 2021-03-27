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

  ReviewDto findByReservationId(Long reservationId);

  List<ReviewGuestDto> reviewBefore(Long memberId);

  List<ReviewGuestDto> reviewAfter(Long memberId);

  List<ReviewGuestDto> reviewGuestList(Long memberId);

  List<ReviewHostDto> reviewHostList(Long memberId);

  List<ReviewGuestDto> guestReplyList(Long memberId);

  List<ReviewHostDto> reviewReservationListAll(Long roomId);

  List<ReviewHostDto> reviewReservationList(Long roomId);

  ReviewDto getReview(Long reviewId);

  void updateReview(ReviewDto reviewDto);

  void deleteReview(ReviewDto reviewDto);

  void updateReply(ReviewDto reviewDto);

  void deleteReply(ReviewDto reviewDto);

  int roomReviewCount(Long roomId);

  int hostReviewCount(Long roomId);

  int guestReplyCount(Long memberId);

  double reviewScoreClean(Long memberId);

  double reviewScoreLocation(Long memberId);

  double reviewScoreService(Long memberId);
}
