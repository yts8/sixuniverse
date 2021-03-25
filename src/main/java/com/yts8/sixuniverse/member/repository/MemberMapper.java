package com.yts8.sixuniverse.member.repository;

import com.yts8.sixuniverse.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

  MemberDto findByEmail(String email);

  MemberDto findById(Long memberId);

  void save(MemberDto memberDto);

  void updateUsername(MemberDto memberDto);

  void updateBirthdate(MemberDto memberDto);

  void updateMobile(MemberDto memberDto);

  void updateAddress(MemberDto memberDto);

  void updateBio(MemberDto memberDto);

  void updatePassword(MemberDto memberDto);

  void updateProfileImg(MemberDto memberDto);

  void updateHostGradeAndRole(MemberDto memberDto);
}
