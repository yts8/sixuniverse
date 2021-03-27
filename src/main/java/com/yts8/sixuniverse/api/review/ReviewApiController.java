package com.yts8.sixuniverse.api.review;

import com.yts8.sixuniverse.review.dto.ReviewGuestDto;
import com.yts8.sixuniverse.review.dto.ReviewHostDto;
import com.yts8.sixuniverse.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewApiController {

  private final ReviewService reviewService;

  @GetMapping("/guest/about/{memberId}")
  public List<Map<String, Object>> guestAboutMe(@PathVariable Long memberId) {
    List<Map<String, Object>> reviews = new ArrayList<>();

    List<ReviewHostDto> reviewHostDtos = reviewService.reviewHostList(memberId);
    for (ReviewHostDto reviewHostDto : reviewHostDtos) {
      Map<String, Object> reviewMap = new HashMap<>();
      reviewMap.put("memberId", reviewHostDto.getGuestId());
      reviewMap.put("username", reviewHostDto.getGuestName());
      reviewMap.put("profileImg", reviewHostDto.getGuestProfileImg());
      reviewMap.put("reviewContent", reviewHostDto.getReviewContent());
      reviewMap.put("reviewRegDate", reviewHostDto.getReviewRegDate());

      reviews.add(reviewMap);
    }
    return reviews;
  }

  @GetMapping("/host/about/{memberId}")
  public List<Map<String, Object>> hostAboutMe(@PathVariable Long memberId) {
    List<Map<String, Object>> reviews = new ArrayList<>();
    List<ReviewGuestDto> reviewGuestDtos = reviewService.guestReplyList(memberId);

    for (ReviewGuestDto reviewGuestDto : reviewGuestDtos) {
      Map<String, Object> reviewMap = new HashMap<>();
      reviewMap.put("memberId", reviewGuestDto.getMemberId());
      reviewMap.put("username", reviewGuestDto.getHostName());
      reviewMap.put("profileImg", reviewGuestDto.getHostProfileImg());
      reviewMap.put("reviewContent", reviewGuestDto.getReplyContent());
      reviewMap.put("reviewRegDate", reviewGuestDto.getReplyRegDate());

      reviews.add(reviewMap);
    }

    return reviews;
  }
}
