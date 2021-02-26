package com.yts8.sixuniverse.member.service;

import com.yts8.sixuniverse.member.domain.Member;

public interface MemberService {

  Member findByEmail(String email);

  Member findById(int memberId);

  void save(Member member);

  void updateMember(Member member);

  void updatePassword(Member member);
}
