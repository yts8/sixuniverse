package com.yts8.sixuniverse.api.member;

import com.yts8.sixuniverse.aws.S3Uploader;
import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.member.dto.MemberPasswordDto;
import com.yts8.sixuniverse.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberSettingApiController {

  private final MemberService memberService;
  private final PasswordEncoder passwordEncoder;
  private final HttpSession httpSession;

  private final S3Uploader s3Uploader;

  @PostMapping("/update/username")
  public void updateUsername(@RequestBody MemberDto memberDto) {
    MemberDto member = (MemberDto) httpSession.getAttribute("member");
    member.setUsername(memberDto.getUsername());
    memberService.updateUsername(member);
  }

  @PostMapping("/update/birthdate")
  public void updateBirthdate(@RequestBody MemberDto memberDto) {
    MemberDto member = (MemberDto) httpSession.getAttribute("member");
    member.setBirthdate(memberDto.getBirthdate());
    memberService.updateBirthdate(member);
  }

  @PostMapping("/update/mobile")
  public void updateMobile(@RequestBody MemberDto memberDto) {
    MemberDto member = (MemberDto) httpSession.getAttribute("member");
    member.setMobile(memberDto.getMobile());
    memberService.updateMobile(member);
  }

  @PostMapping("/update/address")
  public void updateAddress(@RequestBody MemberDto memberDto) {
    MemberDto member = (MemberDto) httpSession.getAttribute("member");
    member.setZipcode(memberDto.getZipcode());
    member.setAddress(memberDto.getAddress());
    member.setSubAddress(memberDto.getSubAddress());
    memberService.updateAddress(member);
  }

  @PostMapping("/update/bio")
  public void updateBio(@RequestBody MemberDto memberDto) {
    MemberDto member = (MemberDto) httpSession.getAttribute("member");
    member.setBio(memberDto.getBio());
    memberService.updateBio(member);
  }

  @PostMapping("/update/password")
  public boolean updatePassword(@RequestBody MemberPasswordDto memberPasswordDto) {
    MemberDto member = (MemberDto) httpSession.getAttribute("member");
    if (!passwordEncoder.matches(memberPasswordDto.getOldPassword(), member.getPassword())) {
      return false;
    }
    String encodeNewPassword = passwordEncoder.encode(memberPasswordDto.getNewPassword());
    member.setPassword(encodeNewPassword);
    memberService.updatePassword(member);

    return true;
  }

  @PostMapping("/update/profile-img")
  public MemberDto updateProfileImg(@RequestParam("profileImg") MultipartFile multipartFile, HttpServletRequest request) {
    String profileImg = null;
    try {
      profileImg = s3Uploader.upload(multipartFile, "member/profile");
    } catch (IOException e) {
      e.printStackTrace();
    }
    MemberDto member = (MemberDto) httpSession.getAttribute("member");
    member.setProfileImg(profileImg);
    memberService.updateProfileImg(member);

    return member;
  }
}
