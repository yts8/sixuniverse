package com.yts8.sixuniverse.performance.controller;

    import com.yts8.sixuniverse.member.domain.Member;
    import com.yts8.sixuniverse.member.service.MemberService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestMapping;

    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.security.Principal;

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

}
