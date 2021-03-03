package com.yts8.sixuniverse.home;

import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@Validated
public class LoginController {

  private final MemberService memberService;
  private final PasswordEncoder passwordEncoder;

  @GetMapping("/login")
  public String login(Model model, Authentication authentication) {
    if (authentication != null) {
      return "redirect:/";
    }
    model.addAttribute("title", "로그인");
    return "home/login";
  }

  @GetMapping("/logout")
  public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
    if (authentication != null) {
      new SecurityContextLogoutHandler().logout(request, response, authentication);
    }
    return "redirect:/login";
  }

  @GetMapping("/join")
  public String join(Model model, Authentication authentication) {
    if (authentication != null) {
      return "redirect:/";
    }
    model.addAttribute("title", "회원가입");
    return "home/join";
  }

  @PostMapping("/join")
  public String join(MemberDto memberDto) {
    memberDto.setRole("GUEST");
    memberDto.setSocial("LOCAL");
    memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
    memberService.save(memberDto);
    return "redirect:/login";
  }

}
