package com.yts8.sixuniverse.member.service;

import com.yts8.sixuniverse.member.dto.MemberDto;
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
  public void save(MemberDto memberDto) {
    memberMapper.save(memberDto);
  }

  @Override
  public void updateMember(MemberDto memberDto) {
    memberMapper.updateMember(memberDto);
  }

  @Override
  public void updatePassword(MemberDto memberDto) {
    memberMapper.updatePassword(memberDto);
  }

  @Override
  public MemberDto findByEmail(String email) {
    return memberMapper.findByEmail(email);
  }

  @Override
  public MemberDto findById(int memberId) {
    return memberMapper.findById(memberId);
  }

}
