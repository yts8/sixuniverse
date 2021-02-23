package com.yts8.sixuniverse.member.service;

import com.yts8.sixuniverse.member.Member;
import com.yts8.sixuniverse.member.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberMapper memberMapper;

  @Override
  public void save(Member member) {
    memberMapper.save(member);
  }

  @Override
  public Member findByEmail(String email) {
    return memberMapper.findByEmail(email);
  }

}
