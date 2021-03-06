package com.yts8.sixuniverse.config.auth.service;

import com.yts8.sixuniverse.config.auth.dto.OAuthMember;
import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@Service("oAuth2UserService")
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

  private final MemberService memberService;
  private final HttpSession httpSession;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

    DefaultOAuth2UserService defaultOAuth2UserService = new DefaultOAuth2UserService();
    OAuth2User oAuth2User = defaultOAuth2UserService.loadUser(userRequest);

    String registrationId = userRequest.getClientRegistration().getRegistrationId();

    MemberDto authMember = null;
    if (registrationId.equals("google")) {
      authMember = OAuthMember.ofGoogle(registrationId, oAuth2User.getAttributes());
    }

    MemberDto member = memberService.findByEmail(authMember.getEmail());

    if (member == null) {
      member = authMember;
      memberService.save(member);
    }

    if (!member.getSocial().equals(registrationId)) {
      OAuth2Error oAuth2Error = new OAuth2Error(registrationId.toUpperCase() + "로 가입되어 있습니다.", null, null);
      throw new OAuth2AuthenticationException(oAuth2Error, oAuth2Error.toString());
    }

    httpSession.setAttribute("member", member);

    return new DefaultOAuth2User(
        Collections.singleton(new SimpleGrantedAuthority("ROLE_" + member.getRole())),
        oAuth2User.getAttributes(),
        userRequest.getClientRegistration()
            .getProviderDetails()
            .getUserInfoEndpoint()
            .getUserNameAttributeName());
  }
}

