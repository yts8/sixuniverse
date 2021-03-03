package com.yts8.sixuniverse.config.auth.service;


import com.yts8.sixuniverse.member.dto.MemberDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MemberContext extends User {

  private final MemberDto memberDto;

  public MemberContext(MemberDto memberDto, Collection<? extends GrantedAuthority> authorities) {
    super(memberDto.getEmail(), memberDto.getPassword(), authorities);
    this.memberDto = memberDto;
  }

  public MemberDto getMemberDto() {
    return memberDto;
  }
}
