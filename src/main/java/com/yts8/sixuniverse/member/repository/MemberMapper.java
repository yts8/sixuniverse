package com.yts8.sixuniverse.member.repository;

import com.yts8.sixuniverse.member.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

  Member findByEmail(String email);

  void save(Member member);
}
