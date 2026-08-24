package pl.coderslab.beans;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class SessionController {
    @GetMapping("/check-session")
    public String check(HttpSession session) {
       if (session.getAttribute("loginStart") == null) {
           session.setAttribute("loginStart", LocalDateTime.now());
           return "no-login-value";
       }
       return session.getAttribute("loginStart").toString();
    }
}
