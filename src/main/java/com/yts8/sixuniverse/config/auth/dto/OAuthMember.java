package com.yts8.sixuniverse.config.auth.dto;

import com.yts8.sixuniverse.member.dto.MemberDto;

import java.util.Map;
import java.util.UUID;

public class OAuthMember {

  public static MemberDto ofGoogle(String registrationId, Map<String, Object> attributes) {
    MemberDto authMember = new MemberDto();
    authMember.setEmail((String) attributes.get("email"));
    authMember.setPassword(UUID.randomUUID().toString() + UUID.randomUUID().toString());
    authMember.setUsername((String) attributes.get("name"));
    authMember.setProfileImg((String) attributes.get("picture"));
    authMember.setSocial(registrationId.toUpperCase());
    authMember.setRole("ROLE_GUEST");
    return authMember;
  }

}
