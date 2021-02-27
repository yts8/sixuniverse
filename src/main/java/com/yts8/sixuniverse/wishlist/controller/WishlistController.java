package com.yts8.sixuniverse.wishlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

  @GetMapping("")
  public String index() {
    return "wishlist/index";
  }

  @GetMapping("/folder")
  public String folder() {
    return "wishlist/wishlist-folder";
  }

}
