package com.yts8.sixuniverse.config.auth.provider;

import com.yts8.sixuniverse.config.auth.service.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

  private final UserDetailsService userDetailsService;
  private final PasswordEncoder passwordEncoder;
  private final HttpSession httpSession;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String email = authentication.getName();
    String password = (String) authentication.getCredentials();

    MemberContext memberContext = (MemberContext) userDetailsService.loadUserByUsername(email);
    if (!passwordEncoder.matches(password, memberContext.getMemberDto().getPassword())) {
      throw new BadCredentialsException("BadCredentialsException");
    }

    httpSession.setAttribute("member", memberContext.getMemberDto());

    return new UsernamePasswordAuthenticationToken(memberContext.getMemberDto().getEmail(), null, memberContext.getAuthorities());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
