package com.yts8.sixuniverse.performance.controller;

    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/performance/*")
public class PerformanceController {

  @GetMapping ("/income")
  public String income() {
    return "performance/income";
  }

  @GetMapping ("/super-host")
  public String superHost() {
    return "performance/super-host";
  }

  @GetMapping ("/views")
  public String views() {
    return "performance/views";
  }

//  @GetMapping ("/#") //후기관리 위치
//  public String review() { return "performance/#";
//  }

}
