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
  public void updateUsername(MemberDto memberDto) {
    memberMapper.updateUsername(memberDto);
  }

  @Override
  public void updateBirthdate(MemberDto memberDto) {
    memberMapper.updateBirthdate(memberDto);
  }

  @Override
  public void updateMobile(MemberDto memberDto) {
    memberMapper.updateMobile(memberDto);
  }

  @Override
  public void updateAddress(MemberDto memberDto) {
    memberMapper.updateAddress(memberDto);
  }

  @Override
  public void updateBio(MemberDto memberDto) {
    memberMapper.updateBio(memberDto);
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
  public MemberDto findById(Long memberId) {
    return memberMapper.findById(memberId);
  }

}
