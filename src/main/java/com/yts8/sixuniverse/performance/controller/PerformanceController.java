package com.yts8.sixuniverse.performance.controller;

import com.yts8.sixuniverse.member.dto.MemberDto;
import com.yts8.sixuniverse.performance.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.text.NumberFormat;

@Controller
@RequiredArgsConstructor
@RequestMapping("/host/performance")
public class PerformanceController {

  private final PerformanceService performanceService;

  @GetMapping("/income")
  public String income() {
    //금액 보낼때 포맷 참고
//    NumberFormat formatter = NumberFormat.getNumberInstance();
//    int num =123456789;
//    System.out.println( formatter.format(num) );    //123,456,789


    return "performance/income";
  }

  @GetMapping("/super-host")
  public String superHost(HttpSession session, Model model) {

    MemberDto member = (MemberDto) session.getAttribute("member");
    NumberFormat formatter = new DecimalFormat("0.#");
    double reviewScore = performanceService.findByReviewScore(member.getMemberId());
    model.addAttribute("reviewScore",  formatter.format(reviewScore));


    return "performance/super-host";
  }

  @GetMapping("/views")
  public String views() {
    return "performance/views";
  }

//  @GetMapping ("/#") //후기관리 위치
//  public String review() { return "performance/#";
//  }

}
