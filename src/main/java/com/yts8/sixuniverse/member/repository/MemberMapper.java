package com.yts8.sixuniverse.member.repository;

import com.yts8.sixuniverse.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

  Member findByEmail(String email);

  void save(Member member);

  void updateMember(Member member);
}
