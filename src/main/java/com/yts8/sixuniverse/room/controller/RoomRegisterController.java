package com.yts8.sixuniverse.room.controller;

import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.room.dto.RoomDto;
import com.yts8.sixuniverse.room.service.RoomService;
import com.yts8.sixuniverse.roomFacility.dto.RoomFacilityDto;
import com.yts8.sixuniverse.roomFacility.service.RoomFacilityService;
import com.yts8.sixuniverse.roomImage.dto.RoomImageDto;
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
  public String getTypes(Model model, HttpSession httpSession, @PathVariable Long roomId) {
    model.addAttribute("title", "유형 선택");
    model.addAttribute("roomDto", roomService.findById(roomId));

    return "room/register/types";
  }

  @PostMapping("/types")
  public String postTypes(HttpSession httpSession, RoomDto roomDto) {
    roomService.updateTypes(roomDto);

    return "redirect:/host/room/register/bedrooms/" + roomDto.getRoomId();
  }

  @GetMapping("/bedrooms/{roomId}")
  public String getBedrooms(Model model, HttpSession httpSession, @PathVariable Long roomId) {
    model.addAttribute("title", "유형 선택");
    model.addAttribute("roomDto", roomService.findById(roomId));

    return "room/register/bedrooms";
  }

  @PostMapping("/bedrooms")
  public String postBedRooms(HttpSession httpSession, RoomDto roomDto) {
    roomService.updateBedrooms(roomDto);

    return "redirect:/host/room/register/amenities/" + roomDto.getRoomId();
  }

  @GetMapping("/amenities/{roomId}")
  public String getAmenities(Model model, HttpSession httpSession, @PathVariable Long roomId) {
    RoomFacilityDto facilityDto = new RoomFacilityDto();
    facilityDto.setRoomId(roomId);
    facilityDto.setCategoryName("amenities");

    model.addAttribute("title", "편의 시설");
    model.addAttribute("amenities", roomFacilityService.findByRoomIdAndCategoryName(facilityDto));

    return "room/register/amenities";
  }

  @PostMapping("/amenities")
  public String postAmenities(HttpSession httpSession, RoomDto roomDto, @RequestParam List<String> name) {

    List<RoomFacilityDto> facilityDtos = new ArrayList<>();
    for (String n : name) {
      RoomFacilityDto facilityDto = new RoomFacilityDto();
      facilityDto.setRoomId(roomDto.getRoomId());
      facilityDto.setName(n);
      facilityDto.setCategoryName("amenities");
      facilityDtos.add(facilityDto);
    }

    roomFacilityService.save(facilityDtos);
    return "redirect:/host/room/register/safety/" + roomDto.getRoomId();
  }

  @GetMapping("/safety/{roomId}")
  public String getSafety(Model model, HttpSession httpSession, @PathVariable Long roomId) {
    RoomFacilityDto facilityDto = new RoomFacilityDto();
    facilityDto.setRoomId(roomId);
    facilityDto.setCategoryName("safety");

    model.addAttribute("title", "안전 시설");
    model.addAttribute("safety", roomFacilityService.findByRoomIdAndCategoryName(facilityDto));

    return "room/register/safety";
  }

  @PostMapping("/safety")
  public String postSafety(HttpSession httpSession, RoomDto roomDto, @RequestParam List<String> name) {

    List<RoomFacilityDto> facilityDtos = new ArrayList<>();
    for (String n : name) {
      RoomFacilityDto facilityDto = new RoomFacilityDto();
      facilityDto.setRoomId(roomDto.getRoomId());
      facilityDto.setName(n);
      facilityDto.setCategoryName("safety");
      facilityDtos.add(facilityDto);
    }

    roomFacilityService.save(facilityDtos);
    return "redirect:/host/room/register/spaces/" + roomDto.getRoomId();
  }

  @GetMapping("/spaces/{roomId}")
  public String getSpaces(Model model, HttpSession httpSession, @PathVariable Long roomId) {
    RoomFacilityDto facilityDto = new RoomFacilityDto();
    facilityDto.setRoomId(roomId);
    facilityDto.setCategoryName("spaces");

    model.addAttribute("title", "게스트 사용 시설");
    model.addAttribute("spaces", roomFacilityService.findByRoomIdAndCategoryName(facilityDto));

    return "room/register/spaces";
  }

  @PostMapping("/spaces")
  public String postSpaces(HttpSession httpSession, RoomDto roomDto, @RequestParam List<String> name) {

    List<RoomFacilityDto> facilityDtos = new ArrayList<>();
    for (String n : name) {
      RoomFacilityDto facilityDto = new RoomFacilityDto();
      facilityDto.setRoomId(roomDto.getRoomId());
      facilityDto.setName(n);
      facilityDto.setCategoryName("spaces");
      facilityDtos.add(facilityDto);
    }

    roomFacilityService.save(facilityDtos);
    return "redirect:/host/room/register/images/" + roomDto.getRoomId();
  }

  @GetMapping("/images/{roomId}")
  public String getImages(Model model, HttpSession httpSession, @PathVariable Long roomId) {
    RoomImageDto ImageDto = new RoomImageDto();
    ImageDto.setRoomId(roomId);

    model.addAttribute("title", "숙소 이미지 등록");
//    model.addAttribute("images", roomImageService.findByRoomId(ImageDto));

    return "room/register/images";
  }

  @PostMapping("/images")
  public String postImages(HttpSession httpSession, RoomDto roomDto) {
    List<RoomImageDto> roomImageDtos = new ArrayList<>();


    return "redirect:/host/room/register/info/" + roomDto.getRoomId();
  }

  @GetMapping("/info/{roomId}")
  public String getInfo(Model model, HttpSession httpSession, @PathVariable Long roomId) {
    model.addAttribute("title", "제목과 내용 설정");
    model.addAttribute("roomDto", roomService.findById(roomId));

    return "room/register/info";
  }

  @PostMapping("/info")
  public String postInfo(HttpSession httpSession, RoomDto roomDto) {
    roomService.updateInfo(roomDto);

    return "redirect:/host/room/register/availability-settings/" + roomDto.getRoomId();
  }

  @GetMapping("/availability-settings/{roomId}")
  public String getAvailabilitySettings(Model model, HttpSession httpSession, @PathVariable Long roomId) {
    model.addAttribute("title", "숙소 예약 정보 설정");
    model.addAttribute("roomDto", roomService.findById(roomId));

    return "room/register/availability-setting";
  }

  @PostMapping("/availability-settings")
  public String postAvailabilitySettings(HttpSession httpSession, RoomDto roomDto) {
    roomService.updateAvailabilitySettings(roomDto);

    return "redirect:/host/room/register/calendar/" + roomDto.getRoomId();
  }

  @GetMapping("/calendar/{roomId}")
  public String getCalendar(Model model, HttpSession httpSession, @PathVariable Long roomId) {
    model.addAttribute("title", "달력 설정");

    LocalDateTime renewDate=roomService.findByRenewDate(roomId);
    model.addAttribute("renewDate", renewDate);

    LocalDateTime yesterdayTime=renewDate.minusDays(1L);
    String yesterday = yesterdayTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    model.addAttribute("yesterday", yesterday);

    int expiryDateNum=roomService.findByExpiryDate(roomId);
    LocalDateTime expiryDate = renewDate.plusMonths(expiryDateNum);
    model.addAttribute("expiryDate", expiryDate);

    String expiryDay = expiryDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    model.addAttribute("expiryDay", expiryDay);

    return "room/register/calendar";
  }

  @PostMapping("/calendar")
  public String postCalendar(HttpSession httpSession, RoomDto roomDto) {

    return "redirect:/host/room/register/price/" + roomDto.getRoomId();
  }

  @GetMapping("/price/{roomId}")
  public String getPrice(Model model, HttpSession httpSession, @PathVariable Long roomId) {
    model.addAttribute("title", "가격 설정");
    model.addAttribute("roomDto", roomService.findById(roomId));

    return "room/register/price";
  }

  @PostMapping("/price")
  public String postPrice(HttpSession httpSession, RoomDto roomDto) {
    roomService.updatePrice(roomDto);
    return "redirect:/";
  }

}