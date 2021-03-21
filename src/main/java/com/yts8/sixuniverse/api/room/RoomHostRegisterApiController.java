package com.yts8.sixuniverse.api.room;

import com.yts8.sixuniverse.aws.S3Uploader;
import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.reservationDate.dto.ReservationDateDto;
import com.yts8.sixuniverse.reservationDate.service.ReservationDateService;
import com.yts8.sixuniverse.room.dto.RoomDto;
import com.yts8.sixuniverse.room.service.RoomService;
import com.yts8.sixuniverse.roomImage.dto.RoomImageDto;
import com.yts8.sixuniverse.roomImage.service.RoomImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/host/room/register")
public class RoomHostRegisterApiController {

  private final RoomImageService roomImageService;
  private final RoomService roomService;
  private final ReservationDateService reservationDateService;
  private final S3Uploader s3Uploader;
  private final HttpSession httpSession;

  @PostMapping("/calendar")
  public void postCalendar(@RequestParam List<String> impossibleDayString, Long roomId) {

    RoomDto roomDto = roomService.findById(roomId);
    MemberDto member = (MemberDto) httpSession.getAttribute("member");

    if (roomDto.getMemberId().equals(member.getMemberId())) {
      List<LocalDate> impossibleDay = new ArrayList<>();//db에 저장할 date타입 예약불가날짜 리스트

      for (String day : impossibleDayString) {
        LocalDate day2 = LocalDate.parse(day);
        impossibleDay.add(day2);
      }

      List<ReservationDateDto> hostReservationDtos = new ArrayList<>();
      for (LocalDate d : impossibleDay) {
        ReservationDateDto reservationDateDto = new ReservationDateDto();
        reservationDateDto.setRoomId(roomId);
        reservationDateDto.setReservationDate(d);
        hostReservationDtos.add(reservationDateDto);
      }
      reservationDateService.hostReservationDateInsert(hostReservationDtos);
    }
  }

  @PostMapping("/images")
  public RoomImageDto postImages(RoomDto room, @RequestParam("roomImg") MultipartFile multipartFile) {

    RoomDto roomDto = roomService.findById(room.getRoomId());
    MemberDto member = (MemberDto) httpSession.getAttribute("member");

    if (!roomDto.getMemberId().equals(member.getMemberId())) {
      return null;
    }

    String roomImg = null;
    try {
      roomImg = s3Uploader.upload(multipartFile, "room/images");
    } catch (IOException e) {
      e.printStackTrace();
    }
    RoomImageDto roomImageDto = new RoomImageDto();
    roomImageDto.setRoomId(roomDto.getRoomId());
    roomImageDto.setRoomImg(roomImg);
    roomImageService.save(roomImageDto);

    return roomImageDto;
  }

  @PostMapping("/images/update")
  public RoomImageDto updateImages(@RequestParam("roomImg") MultipartFile multipartFile, Long roomImgId, Long roomId) {
    RoomDto roomDto = roomService.findById(roomId);
    MemberDto member = (MemberDto) httpSession.getAttribute("member");

    if (!roomDto.getMemberId().equals(member.getMemberId())) {
      return null;
    }

    String roomImg = null;
    try {
      roomImg = s3Uploader.upload(multipartFile, "room/images");
    } catch (IOException e) {
      e.printStackTrace();
    }
    RoomImageDto roomImageDto = new RoomImageDto();
    roomImageDto.setRoomImgId(roomImgId);
    roomImageDto.setRoomId(roomId);
    roomImageDto.setRoomImg(roomImg);
    roomImageService.updateImage(roomImageDto);

    return roomImageDto;
  }
}
