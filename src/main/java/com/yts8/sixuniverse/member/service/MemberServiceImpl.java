package com.yts8.sixuniverse.member.service;

import com.yts8.sixuniverse.member.domain.Member;
import com.yts8.sixuniverse.member.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

  private final MemberMapper memberMapper;

  @Override
  public void save(Member member) {
    memberMapper.save(member);
  }

  @Override
  public void updateMember(Member member) {
    memberMapper.updateMember(member);
  }

  @Override
  public Member findByEmail(String email) {
    return memberMapper.findByEmail(email);
  }

}
