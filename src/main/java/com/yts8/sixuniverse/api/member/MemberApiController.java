package com.yts8.sixuniverse.api.member;

import com.yts8.sixuniverse.aws.S3Uploader;
import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberApiController {

  private final MemberService memberService;
  private final HttpSession httpSession;
  private final S3Uploader s3Uploader;

  @PostMapping("/update/profile-img")
  public Map<String, String> updateProfileImg(@RequestParam("profileImg") MultipartFile multipartFile) {
    String profileImg = null;
    try {
      profileImg = s3Uploader.upload(multipartFile, "member/profile");
    } catch (IOException e) {
      e.printStackTrace();
    }
    MemberDto member = (MemberDto) httpSession.getAttribute("member");
    member.setProfileImg(profileImg);
    memberService.updateProfileImg(member);

    Map<String, String> map = new HashMap<>();
    map.put("profileImg", profileImg);

    return map;
  }
}
