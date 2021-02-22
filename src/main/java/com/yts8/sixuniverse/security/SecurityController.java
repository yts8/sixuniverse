package com.yts8.sixuniverse.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

  @GetMapping("/")
  public String index() {
    return "index";
  }
}
