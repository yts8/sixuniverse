package com.yts8.sixuniverse.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("user").password("{noop}1234").roles("MEMBER");
    auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
      .antMatchers("/", "/css/**", "/js/**", "/img/**", "/login", "/join", "/room/detail/**").permitAll()
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
      })
      .and()
      .rememberMe()
      .rememberMeParameter("remember")
      .tokenValiditySeconds(60 * 60 * 24)
      .alwaysRemember(false)
      .userDetailsService(userDetailsService());

  }
}
