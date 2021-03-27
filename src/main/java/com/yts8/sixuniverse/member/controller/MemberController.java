package com.yts8.sixuniverse.member.controller;

import com.yts8.sixuniverse.member.service.MemberService;
import com.yts8.sixuniverse.review.dto.ReviewGuestDto;
import com.yts8.sixuniverse.review.service.ReviewService;
import com.yts8.sixuniverse.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

  private final MemberService memberService;
  private final RoomService roomService;
  private final ReviewService reviewService;

  @GetMapping("/profile/{memberId}")
  public String profile(Model model, @PathVariable Long memberId) {

    model.addAttribute("memberDto", memberService.findById(memberId));
    model.addAttribute("roomInfoDtos", roomService.roomInfoFindByMemberId(memberId));
    model.addAttribute("guestReviewCount", reviewService.hostReviewCount(memberId));
    model.addAttribute("hostReviewCount", reviewService.guestReplyCount(memberId));

    model.addAttribute("title", "프로필");
    return "member/profile";
  }
}
