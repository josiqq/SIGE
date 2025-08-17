package com.sige.controller;

import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController implements ErrorController {
   @GetMapping({"/login"})
   public String login(@AuthenticationPrincipal User user) {
      return user != null ? "redirect:/" : "login";
   }

   @GetMapping({"/403"})
   public String accesoNegado() {
      return "/403";
   }

   @GetMapping({"/500"})
   public String errordeServidor() {
      return "/error";
   }

   @GetMapping({"/error"})
   public ModelAndView handleError(HttpServletResponse response) {
      ModelAndView modelAndView = new ModelAndView();
      if (response.getStatus() == HttpStatus.NOT_FOUND.value()) {
         modelAndView.setViewName("404");
      } else if (response.getStatus() == HttpStatus.FORBIDDEN.value()) {
         modelAndView.setViewName("403");
      } else if (response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
         modelAndView.setViewName("500");
      } else {
         modelAndView.setViewName("error");
      }

      return modelAndView;
   }
}
