package com.yts8.sixuniverse.member.controller;

import com.yts8.sixuniverse.member.domain.Member;
import com.yts8.sixuniverse.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class LoginController {

  private final MemberService memberService;
  private final PasswordEncoder passwordEncoder;

  @GetMapping("/login")
  public String login(Principal principal) {
    if (principal != null) {
      return "redirect:/";
    }
    return "member/login";
  }

  @GetMapping("/logout")
  public String logout(HttpServletRequest request, HttpServletResponse response) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
      new SecurityContextLogoutHandler().logout(request, response, authentication);
    }
    return "redirect:/login";
  }

  @GetMapping("/join")
  public String join(Principal principal) {
    if (principal != null) {
      return "redirect:/";
    }
    return "member/join";
  }

  @PostMapping("/join")
  public String join(Member member) {
    member.setRole("MEMBER");
    member.setSocial("LOCAL");
    member.setPassword(passwordEncoder.encode(member.getPassword()));
    memberService.save(member);
    return "redirect:/login";
  }

}
