package com.yts8.sixuniverse.member.service;

import com.yts8.sixuniverse.member.domain.Member;

public interface MemberService {

  Member findByEmail(String email);

  void save(Member member);
}
