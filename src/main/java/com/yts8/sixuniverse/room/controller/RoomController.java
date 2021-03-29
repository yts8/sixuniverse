package com.yts8.sixuniverse.room.controller;

import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.member.service.MemberService;
import com.yts8.sixuniverse.reservationDate.service.ReservationDateService;
import com.yts8.sixuniverse.review.dto.ReviewHostDto;
import com.yts8.sixuniverse.review.service.ReviewService;
import com.yts8.sixuniverse.room.dto.RoomDto;
import com.yts8.sixuniverse.room.service.RoomService;
import com.yts8.sixuniverse.roomFacility.dto.RoomFacilityDto;
import com.yts8.sixuniverse.roomFacility.service.RoomFacilityService;
import com.yts8.sixuniverse.roomHits.dto.RoomHitsDto;
import com.yts8.sixuniverse.roomHits.service.RoomHitsService;
import com.yts8.sixuniverse.roomImage.service.RoomImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {
  private final RoomService roomService;
  private final RoomImageService roomImageService;
  private final RoomHitsService roomHitsService;
  private final ReservationDateService reservationDateService;
  private final RoomFacilityService roomFacilityService;
  private final ReviewService reviewService;
  private final MemberService memberService;


  @GetMapping("/detail/{roomId}")
  public String detail(Model model, HttpSession httpSession, @PathVariable Long roomId) {

    RoomDto roomDto = roomService.findById(roomId);
    if (roomDto == null || !roomDto.getStatus().equals("register")) {
      return "redirect:/";
    }

    MemberDto member = (MemberDto) httpSession.getAttribute("member");
    if (member == null || !roomDto.getMemberId().equals(member.getMemberId())) {
      RoomHitsDto roomHitsDto = new RoomHitsDto();
      roomHitsDto.setRoomId(roomId);
      roomHitsDto.setReadDate(LocalDate.now());

      RoomHitsDto findRoomHitsDto = roomHitsService.findByRoomIdAndReadDate(roomHitsDto);
      if (findRoomHitsDto == null) {
        roomHitsService.save(roomHitsDto);
      } else {
        roomHitsService.addHits(findRoomHitsDto.getRoomHitsId());
      }
    }

    List<LocalDate> reservationDateList = reservationDateService.reservationDateList(roomId);
    Collections.sort(reservationDateList);

    model.addAttribute("title", "숙소 상세 정보");
    model.addAttribute("room", roomDto);
    model.addAttribute("reservationDateList", reservationDateList);
    model.addAttribute("roomImages", roomImageService.findByRoomId(roomDto.getRoomId()));

    String hostName = roomService.findByHostName(roomDto.getRoomId());
    model.addAttribute("hostName", hostName);

    MemberDto memberDto = memberService.findById(roomDto.getMemberId());
    model.addAttribute("hostId", memberDto.getMemberId());

    RoomFacilityDto facilityDto = new RoomFacilityDto();
    facilityDto.setRoomId(roomId);
    List<String> roomFacilityList = roomFacilityService.selectRoomFacility(facilityDto);
    model.addAttribute("roomFacilityList", roomFacilityList);

    List<ReviewHostDto> reviewReservationListAll = reviewService.reviewReservationListAll(roomDto.getRoomId());
    model.addAttribute("reviewReservationListAll", reviewReservationListAll);

    List<ReviewHostDto> reviewReservationList = reviewService.reviewReservationList(roomDto.getRoomId());
    model.addAttribute("reviewReservationList", reviewReservationList);

    NumberFormat formatter = new DecimalFormat("0.#");
    double reviewScoreAll = reviewService.reviewScoreAll(roomDto.getRoomId());
    model.addAttribute("reviewScore", formatter.format(reviewScoreAll));

    double reviewScoreClean = reviewService.reviewScoreClean(roomDto.getRoomId());
    model.addAttribute("reviewScoreClean", formatter.format(reviewScoreClean));

    double reviewScoreLocation = reviewService.reviewScoreLocation(roomDto.getRoomId());
    model.addAttribute("reviewScoreLocation", formatter.format(reviewScoreLocation));

    double reviewScoreService = reviewService.reviewScoreService(roomDto.getRoomId());
    model.addAttribute("reviewScoreService", formatter.format(reviewScoreService));

    int reviewCount = reviewService.roomReviewCount(roomDto.getRoomId());
    model.addAttribute("reviewCount", reviewCount);

    return "room/detail";
  }

}