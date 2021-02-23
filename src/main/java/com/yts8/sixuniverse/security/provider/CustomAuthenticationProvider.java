package com.yts8.sixuniverse.security.provider;

import com.yts8.sixuniverse.security.service.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

  private final UserDetailsService userDetailsService;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String email = authentication.getName();
    String password = (String) authentication.getCredentials();

    MemberContext memberContext = (MemberContext) userDetailsService.loadUserByUsername(email);
    if (!passwordEncoder.matches(password, memberContext.getMember().getPassword())) {
      throw new BadCredentialsException("BadCredentialsException");
    }

    return new UsernamePasswordAuthenticationToken(memberContext.getPassword(), null, memberContext.getAuthorities());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
