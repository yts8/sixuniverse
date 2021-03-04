package com.yts8.sixuniverse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan(basePackages = {"com.yts8.sixuniverse.*.repository"})
public class SixuniverseApplication {

  public static void main(String[] args) {
    SpringApplication.run(SixuniverseApplication.class, args);
  }

}
