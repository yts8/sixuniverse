package com.yts8.sixuniverse.security.service;

import com.yts8.sixuniverse.member.domain.Member;
import com.yts8.sixuniverse.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

  private final MemberService memberService;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Member member = memberService.findByEmail(email);
    if (member == null) {
      throw new UsernameNotFoundException("UsernameNotFoundException");
    }

    List<GrantedAuthority> roles = new ArrayList<>();
    roles.add(new SimpleGrantedAuthority("ROLE_" + member.getRole()));

    return new MemberContext(member, roles);
  }

}
