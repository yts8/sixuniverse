package com.yts8.sixuniverse.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member/setting")
public class MemberSettingController {

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
    return "member/setting/password/index";
  }

  @GetMapping("/password/reset")
  public String getPasswordReset(Model model) {
    model.addAttribute("title", "비밀번호 재설정");
    return "member/setting/password/reset";
  }
}
