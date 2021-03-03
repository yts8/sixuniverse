package com.yts8.sixuniverse.api.home;

import com.yts8.sixuniverse.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginApiController {

  private final MemberService memberService;

  @GetMapping("/email/{email}")
  public boolean getEmail(@PathVariable String email) {
    return memberService.findByEmail(email) == null;
  }
}
