package com.yts8.sixuniverse.review.service;

import com.yts8.sixuniverse.review.dto.ReviewDto;
import com.yts8.sixuniverse.review.dto.ReviewGuestDto;
import com.yts8.sixuniverse.review.dto.ReviewHostDto;
import com.yts8.sixuniverse.review.repository.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

  private final ReviewMapper reviewMapper;

  @Override
  public void insertReview(ReviewDto reviewDto) {
    reviewMapper.insertReview(reviewDto);
  }

  @Override
  public ReviewDto findById(Long reviewId) {
    return reviewMapper.findById(reviewId);
  }

  @Override
  public ReviewDto findByReservationId(Long reservationId) {
    return reviewMapper.findByReservationId(reservationId);
  }

  @Override
  public List<ReviewGuestDto> reviewBefore(Long memberId) {
    return reviewMapper.reviewBefore(memberId);
  }

  @Override
  public List<ReviewGuestDto> reviewAfter(Long memberId) {
    return reviewMapper.reviewAfter(memberId);
  }

  @Override
  public List<ReviewGuestDto> reviewGuestList(Long memberId) {
    return reviewMapper.reviewGuestList(memberId);
  }

  @Override
  public List<ReviewHostDto> reviewHostList(Long memberId) {
    return reviewMapper.reviewHostList(memberId);
  }

  @Override
  public List<ReviewGuestDto> guestReplyList(Long memberId) {
    return reviewMapper.guestReplyList(memberId);
  }

  @Override
  public List<ReviewHostDto> reviewReservationListAll(Long roomId) {
    return reviewMapper.reviewReservationListAll(roomId);
  }

  @Override
  public List<ReviewHostDto> reviewReservationList(Long roomId) {
    return reviewMapper.reviewReservationList(roomId);
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

  @Override
  public void updateReply(ReviewDto reviewDto) {
    reviewMapper.updateReply(reviewDto);
  }

  @Override
  public void deleteReply(ReviewDto reviewDto) {
    reviewMapper.deleteReply(reviewDto);
  }

  @Override
  public int roomReviewCount(Long roomId) {
    return reviewMapper.roomReviewCount(roomId);
  }

  @Override
  public int hostReviewCount(Long memberId) {
    return reviewMapper.hostReviewCount(memberId);
  }

  @Override
  public int guestReplyCount(Long memberId) {
    return reviewMapper.guestReplyCount(memberId);
  }

  @Override
  public double reviewScoreClean(Long memberId) {
    return reviewMapper.reviewScoreClean(memberId);
  }

  @Override
  public double reviewScoreLocation(Long memberId) {
    return reviewMapper.reviewScoreLocation(memberId);
  }

  @Override
  public double reviewScoreService(Long memberId) {
    return reviewMapper.reviewScoreService(memberId);
  }

}
