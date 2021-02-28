package com.yts8.sixuniverse.member.service;

import com.yts8.sixuniverse.member.dto.MemberDto;

public interface MemberService {

  MemberDto findByEmail(String email);

  MemberDto findById(int memberId);

  void save(MemberDto memberDto);

  void updateMember(MemberDto memberDto);

  void updatePassword(MemberDto memberDto);
}
