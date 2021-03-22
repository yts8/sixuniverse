package com.yts8.sixuniverse.api.room;

import com.yts8.sixuniverse.aws.S3Uploader;
import com.yts8.sixuniverse.reservationDate.dto.ReservationDateDto;
import com.yts8.sixuniverse.reservationDate.service.ReservationDateService;
import com.yts8.sixuniverse.room.dto.RoomDto;
import com.yts8.sixuniverse.roomImage.dto.RoomImageDto;
import com.yts8.sixuniverse.roomImage.service.RoomImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/host/room/register")
public class RoomHostRegisterApiController {

  private final RoomImageService roomImageService;
  private final ReservationDateService reservationDateService;
  private final S3Uploader s3Uploader;

  @PostMapping("/calendar")
  public void postCalendar(RoomDto roomDto, @RequestParam List<String> impossibleDayString) {

    List<LocalDate> impossibleDay = new ArrayList<>();//db에 저장할 date타입 예약불가날짜 리스트

    for (String day : impossibleDayString) {
      LocalDate day2 = LocalDate.parse(day);
      impossibleDay.add(day2);
    }

    List<ReservationDateDto> hostReservationDtos = new ArrayList<>();
    for (LocalDate d : impossibleDay) {
      ReservationDateDto reservationDateDto = new ReservationDateDto();
      reservationDateDto.setRoomId(roomDto.getRoomId());
      reservationDateDto.setReservationDate(d);
      hostReservationDtos.add(reservationDateDto);
    }
    reservationDateService.hostReservationDateInsert(hostReservationDtos);
  }

  @PostMapping("/images")
  public RoomImageDto postImages(RoomDto roomDto, @RequestParam("roomImg") MultipartFile multipartFile) {

    RoomImageDto roomImageDto = new RoomImageDto();
    try {
      String roomImg = s3Uploader.upload(multipartFile, "room/images");
      roomImageDto.setRoomId(roomDto.getRoomId());
      roomImageDto.setRoomImg(roomImg);
      roomImageService.save(roomImageDto);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return roomImageDto;
  }

  @PostMapping("/images/update")
  public RoomImageDto postImagesUpdate(RoomDto roomDto, @RequestParam("roomImg") MultipartFile multipartFile, Long roomImgId) {

    RoomImageDto roomImageDto = new RoomImageDto();
    try {
      String roomImg = s3Uploader.upload(multipartFile, "room/images");
      roomImageDto.setRoomImgId(roomImgId);
      roomImageDto.setRoomId(roomDto.getRoomId());
      roomImageDto.setRoomImg(roomImg);
      roomImageService.updateImage(roomImageDto);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return roomImageDto;
  }
}
