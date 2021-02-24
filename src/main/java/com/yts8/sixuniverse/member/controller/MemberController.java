package com.yts8.sixuniverse.member.controller;

import com.yts8.sixuniverse.member.domain.Member;
import com.yts8.sixuniverse.member.service.MemberService;
import com.yts8.sixuniverse.security.service.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

  private final MemberService memberService;
  private final UserDetailsService userDetailsService;

  @GetMapping("/profile")
  public String profile(Model model) {
    model.addAttribute("title", "회원정보");
    return "member/profile";
  }

  @GetMapping("/update")
  public String getUpdate(Model model) {
    model.addAttribute("title", "회원수정");
    return "member/update";
  }

  @PostMapping("/update")
  public String postUpdate(Member member, Authentication authentication) {
    Member authMember = (Member) authentication.getPrincipal();
    member.setEmail(authMember.getEmail());
    memberService.updateMember(member);

    MemberContext memberContext = (MemberContext) userDetailsService.loadUserByUsername(authMember.getEmail());
    SecurityContextHolder
        .getContext()
        .setAuthentication(
            new UsernamePasswordAuthenticationToken(memberContext.getMember(), null, memberContext.getAuthorities()));

    return "redirect:/member/profile";
  }

  // TODO: 비밀번호 수정
  // TODO: 회원탈퇴
}
