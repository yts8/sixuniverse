package com.yts8.sixuniverse.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;
  private final AuthenticationProvider authenticationProvider;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.authenticationProvider(authenticationProvider);
    auth.inMemoryAuthentication().withUser("user").password("{noop}1234").roles("USER");
    auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN");
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
      .and().ignoring().antMatchers("/favicon.ico");

  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
      .antMatchers("/", "/error", "/login", "/join", "/room/detail/**", "/performance/**","/reservation/**").permitAll()
      .antMatchers("/member/**").hasAnyRole("MEMBER", "ADMIN")
      .antMatchers("/admin/**").hasRole("ADMIN")
      .anyRequest().authenticated()
      .and()
      .formLogin()
      .usernameParameter("email")
      .passwordParameter("password")
      .loginPage("/login")
      .successHandler((request, response, authentication) -> {
        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        response.sendRedirect(savedRequest == null ? "/" : savedRequest.getRedirectUrl());
      });
  }
}
