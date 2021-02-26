package com.yts8.sixuniverse.home;

import com.yts8.sixuniverse.member.domain.Member;
import com.yts8.sixuniverse.member.dto.JoinDto;
import com.yts8.sixuniverse.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
import javax.validation.Valid;

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
  public String join(@Valid JoinDto joinDto) {
    ModelMapper modelMapper = new ModelMapper();
    Member member = modelMapper.map(joinDto, Member.class);
    member.setRole("MEMBER");
    member.setSocial("LOCAL");
    member.setPassword(passwordEncoder.encode(member.getPassword()));
    memberService.save(member);
    return "redirect:/login";
  }

}
