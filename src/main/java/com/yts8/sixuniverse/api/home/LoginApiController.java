package com.yts8.sixuniverse.api.home;

import com.yts8.sixuniverse.member.domain.Member;
import com.yts8.sixuniverse.member.dto.JoinDto;
import com.yts8.sixuniverse.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
  public JoinDto getEmail(@PathVariable String email) {
    Member member = memberService.findByEmail(email);
    ModelMapper modelMapper = new ModelMapper();
    if (member == null) {
      return null;
    }
    return modelMapper.map(member, JoinDto.class);
  }
}
