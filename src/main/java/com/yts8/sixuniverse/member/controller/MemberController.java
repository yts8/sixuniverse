package com.yts8.sixuniverse.member.controller;

import com.yts8.sixuniverse.member.domain.Member;
import com.yts8.sixuniverse.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/profile/{memberId}")
  public String profile(Model model, @PathVariable int memberId) {
    Member member = memberService.findById(memberId);

    model.addAttribute("member", member);
    model.addAttribute("title", "프로필");
    return "member/profile";
  }
}
