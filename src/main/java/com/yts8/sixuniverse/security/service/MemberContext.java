package com.yts8.sixuniverse.security.service;


import com.yts8.sixuniverse.member.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MemberContext extends User {

  private final Member member;

  public MemberContext(Member member, Collection<? extends GrantedAuthority> authorities) {
    super(member.getEmail(), member.getPassword(), authorities);
    this.member = member;
  }

  public Member getMember() {
    return member;
  }
}
