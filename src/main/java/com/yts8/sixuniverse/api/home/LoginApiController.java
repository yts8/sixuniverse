package com.yts8.sixuniverse.api.home;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.yts8.sixuniverse.mail.SendMailgun;
import com.yts8.sixuniverse.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginApiController {

  private final MemberService memberService;
  private final SendMailgun sendMailgun;

  @GetMapping("/email/{email}")
  public boolean getEmail(@PathVariable String email) {
    return memberService.findByEmail(email) == null;
  }

  @GetMapping("/email/{email}/auth-code")
  public Map<String, String> getAuthCode(@PathVariable String email) throws UnirestException {
    String subject = "Sixuniverse 인증코드";
    String authCode = UUID.randomUUID().toString();
    sendMailgun.sendMail(email, subject, authCode);
    
    Map<String, String> map = new HashMap<>();
    map.put("authCode", authCode);

    return map;
  }
}
