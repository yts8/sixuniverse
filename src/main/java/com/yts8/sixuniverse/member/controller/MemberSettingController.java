package com.yts8.sixuniverse.member.controller;

import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member/setting")
public class MemberSettingController {

  private final MemberService memberService;
  private final PasswordEncoder passwordEncoder;

  @GetMapping("")
  public String index(Model model) {
    model.addAttribute("title", "계정 관리");
    return "member/setting/index";
  }

  @GetMapping("/personal-info")
  public String getPersonalInfo(Model model) {
    model.addAttribute("title", "개인정보");
    return "member/setting/personal-info";
  }

  @GetMapping("/password")
  public String getPassword(Model model) {
    model.addAttribute("title", "비밀번호 변경");
    return "member/setting/password/update";
  }

  @PostMapping("/password/update")
  public String updatePassword(String oldPassword, String newPassword, Authentication authentication) {
    MemberDto memberDto = (MemberDto) authentication.getPrincipal();

    if (!passwordEncoder.matches(oldPassword, memberDto.getPassword())) {
      return "redirect:/member/setting/password";
    }
    String encodeNewPassword = passwordEncoder.encode(newPassword);
    memberDto.setPassword(encodeNewPassword);
    memberService.updatePassword(memberDto);
    return "redirect:/member/setting";
  }

  @GetMapping("/password/reset")
  public String getPasswordReset(Model model) {
    model.addAttribute("title", "비밀번호 재설정");
    return "member/setting/password/reset";
  }
}
