package com.yts8.sixuniverse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.yts8.sixuniverse.*.repository"})
@SpringBootApplication
public class SixuniverseApplication {

  public static void main(String[] args) {
    SpringApplication.run(SixuniverseApplication.class, args);
  }

}
