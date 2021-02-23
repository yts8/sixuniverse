package com.yts8.sixuniverse.member;

import com.yts8.sixuniverse.member.domain.Member;
import com.yts8.sixuniverse.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
