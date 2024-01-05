package org.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
public class LoginControl {
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user, HttpSession session){
//        String sessionId = UUID.randomUUID().toString();
        session.setAttribute("user",user.getAccount());
        return ResponseEntity.ok("成功");
    }
}
