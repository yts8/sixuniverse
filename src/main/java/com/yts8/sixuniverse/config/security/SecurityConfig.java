package com.yts8.sixuniverse.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final AuthenticationProvider authenticationProvider;
  private final OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider);
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());

  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/",
            "/error",
            "/login/**",
            "/api/login/**",
            "/join",
            "/api/reservation/before",
            "/api/reservation/guest/update/today",
            "/member/setting/password/reset",
            "/room/detail/**").permitAll()
        .antMatchers("/member/**", "/api/member/**").hasAnyRole("GUEST", "HOST", "ADMIN")
        .antMatchers("/host/**", "/api/host/**").hasAnyRole("HOST", "ADMIN")
        .antMatchers("/admin/**").hasRole("ADMIN")
        .anyRequest().authenticated();

    http
        .formLogin()
        .usernameParameter("email")
        .passwordParameter("password")
        .loginPage("/login")
        .successHandler((request, response, authentication) -> {
          RequestCache requestCache = new HttpSessionRequestCache();
          SavedRequest savedRequest = requestCache.getRequest(request, response);
          response.sendRedirect(savedRequest == null ? "/" : savedRequest.getRedirectUrl());
        })
        .and()
        .oauth2Login()
        .loginPage("/login")
        .failureHandler((request, response, exception) -> {
          request.setAttribute("loginError", exception.getMessage());
          request.getRequestDispatcher("/login").forward(request, response);
        })
        .userInfoEndpoint()
        .userService(oAuth2UserService);

    http
        .sessionManagement()
        .maximumSessions(1)
        .maxSessionsPreventsLogin(false);
  }
}
