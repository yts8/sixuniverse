package com.yts8.sixuniverse.member.repository;

import com.yts8.sixuniverse.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

  MemberDto findByEmail(String email);

  MemberDto findById(int memberId);

  void save(MemberDto memberDto);

  void updateMember(MemberDto member);

  void updatePassword(MemberDto member);
}
