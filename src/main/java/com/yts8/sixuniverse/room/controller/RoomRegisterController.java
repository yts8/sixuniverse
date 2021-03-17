package com.yts8.sixuniverse.room.controller;

import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.room.dto.RoomDto;
import com.yts8.sixuniverse.room.service.RoomService;
import com.yts8.sixuniverse.roomFacility.dto.RoomFacilityDto;
import com.yts8.sixuniverse.roomFacility.service.RoomFacilityService;
import com.yts8.sixuniverse.roomImage.service.RoomImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/host/room/register")
public class RoomRegisterController {

  private final RoomService roomService;
  private final RoomFacilityService roomFacilityService;
  private final RoomImageService roomImageService;

  @GetMapping("")
  public String getAddress(Model model) {
    model.addAttribute("title", "숙소등록");
    return "room/register/index";
  }

  @PostMapping("/address")
  public String postAddress(HttpSession httpSession, RoomDto roomDto) {
    MemberDto member = (MemberDto) httpSession.getAttribute("member");
    roomDto.setMemberId(member.getMemberId());
    roomService.save(roomDto);

    return "redirect:/host/room/register/types/" + roomDto.getRoomId();
  }

  @GetMapping("/types/{roomId}")
  public String getTypes(Model model, @PathVariable Long roomId) {
    model.addAttribute("title", "유형 선택");
    model.addAttribute("roomDto", roomService.findById(roomId));

    return "room/register/types";
  }

  @PostMapping("/types/update")
  public String postTypes(RoomDto roomDto) {
    roomService.updateTypes(roomDto);

    return "redirect:/host/room/register/bedrooms/" + roomDto.getRoomId();
  }

  @GetMapping("/bedrooms/{roomId}")
  public String getBedrooms(Model model, @PathVariable Long roomId) {
    model.addAttribute("title", "유형 선택");
    model.addAttribute("roomDto", roomService.findById(roomId));

    return "room/register/bedrooms";
  }

  @PostMapping("/bedrooms/update")
  public String postBedRooms(RoomDto roomDto) {
    roomService.updateBedrooms(roomDto);

    return "redirect:/host/room/register/amenities/" + roomDto.getRoomId();
  }

  @GetMapping("/amenities/{roomId}")
  public String getAmenities(Model model, @PathVariable Long roomId) {
    RoomFacilityDto facilityDto = new RoomFacilityDto();
    facilityDto.setRoomId(roomId);
    facilityDto.setCategoryName("amenities");

    model.addAttribute("title", "편의 시설");
    model.addAttribute("amenities", roomFacilityService.findByRoomIdAndCategoryName(facilityDto));

    return "room/register/amenities";
  }

  @PostMapping("/amenities")
  public String postAmenities(RoomDto roomDto, @RequestParam(value = "name") List<String> names) {

    List<RoomFacilityDto> facilityDtos = new ArrayList<>();
    for (String name : names) {
      RoomFacilityDto facilityDto = new RoomFacilityDto();
      facilityDto.setRoomId(roomDto.getRoomId());
      facilityDto.setName(name);
      facilityDto.setCategoryName("amenities");
      facilityDtos.add(facilityDto);
    }

    roomFacilityService.save(facilityDtos);
    return "redirect:/host/room/register/safety/" + roomDto.getRoomId();
  }

  @GetMapping("/safety/{roomId}")
  public String getSafety(Model model, @PathVariable Long roomId) {
    RoomFacilityDto facilityDto = new RoomFacilityDto();
    facilityDto.setRoomId(roomId);
    facilityDto.setCategoryName("safety");

    model.addAttribute("title", "안전 시설");
    model.addAttribute("safety", roomFacilityService.findByRoomIdAndCategoryName(facilityDto));

    return "room/register/safety";
  }

  @PostMapping("/safety")
  public String postSafety(RoomDto roomDto, @RequestParam(value = "name") List<String> names) {

    List<RoomFacilityDto> facilityDtos = new ArrayList<>();
    for (String name : names) {
      RoomFacilityDto facilityDto = new RoomFacilityDto();
      facilityDto.setRoomId(roomDto.getRoomId());
      facilityDto.setName(name);
      facilityDto.setCategoryName("safety");
      facilityDtos.add(facilityDto);
    }

    roomFacilityService.save(facilityDtos);
    return "redirect:/host/room/register/spaces/" + roomDto.getRoomId();
  }

  @GetMapping("/spaces/{roomId}")
  public String getSpacesFacility(Model model, @PathVariable Long roomId) {
    RoomFacilityDto facilityDto = new RoomFacilityDto();
    facilityDto.setRoomId(roomId);
    facilityDto.setCategoryName("spaces");

    model.addAttribute("title", "게스트 사용 시설");
    model.addAttribute("spaces", roomFacilityService.findByRoomIdAndCategoryName(facilityDto));

    return "room/register/spaces";
  }

  @PostMapping("/spaces")
  public String postSpacesFacility(RoomDto roomDto, @RequestParam(value = "name") List<String> names) {

    List<RoomFacilityDto> facilityDtos = new ArrayList<>();
    for (String name : names) {
      RoomFacilityDto facilityDto = new RoomFacilityDto();
      facilityDto.setRoomId(roomDto.getRoomId());
      facilityDto.setName(name);
      facilityDto.setCategoryName("spaces");
      facilityDtos.add(facilityDto);
    }

    roomFacilityService.save(facilityDtos);
    return "redirect:/host/room/register/images/" + roomDto.getRoomId();
  }

  @GetMapping("/images/{roomId}")
  public String getImages(Model model, @PathVariable Long roomId) {

    model.addAttribute("title", "숙소 이미지 등록");
    model.addAttribute("images", roomImageService.findByRoomId(roomId));

    return "room/register/images";
  }

  @GetMapping("/info/{roomId}")
  public String getInfo(Model model, @PathVariable Long roomId) {
    model.addAttribute("title", "제목과 내용 설정");
    model.addAttribute("roomDto", roomService.findById(roomId));

    return "room/register/info";
  }

  @PostMapping("/info/update")
  public String postInfo(RoomDto roomDto) {
    roomService.updateInfo(roomDto);

    return "redirect:/host/room/register/availability-settings/" + roomDto.getRoomId();
  }

  @GetMapping("/availability-settings/{roomId}")
  public String getAvailabilitySettings(Model model, @PathVariable Long roomId) {
    model.addAttribute("title", "숙소 예약 정보 설정");
    model.addAttribute("roomDto", roomService.findById(roomId));

    return "room/register/availability-setting";
  }

  @PostMapping("/availability-settings/update")
  public String postAvailabilitySettings(RoomDto roomDto) {
    roomService.updateAvailabilitySettings(roomDto);

    return "redirect:/host/room/register/calendar/" + roomDto.getRoomId();
  }

  @GetMapping("/calendar/{roomId}")
  public String getCalendar(Model model, @PathVariable Long roomId) {
    model.addAttribute("title", "달력 설정");

    LocalDateTime renewDate = roomService.findByRenewDate(roomId);
    model.addAttribute("renewDate", renewDate);

    LocalDateTime yesterdayTime = renewDate.minusDays(1L);
    String yesterday = yesterdayTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    model.addAttribute("yesterday", yesterday);

    int expiryDateNum = roomService.findByExpiryDate(roomId);
    LocalDateTime expiryDate = renewDate.plusMonths(expiryDateNum);
    model.addAttribute("expiryDate", expiryDate);

    String expiryDay = expiryDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    model.addAttribute("expiryDay", expiryDay);

    return "room/register/calendar";
  }

  @GetMapping("/price/{roomId}")
  public String getPrice(Model model, @PathVariable Long roomId) {
    model.addAttribute("title", "가격 설정");
    model.addAttribute("roomDto", roomService.findById(roomId));

    return "room/register/price";
  }

  @PostMapping("/price/update")
  public String postPrice(RoomDto roomDto) {
    roomService.updatePrice(roomDto);
    return "redirect:/";
  }

}