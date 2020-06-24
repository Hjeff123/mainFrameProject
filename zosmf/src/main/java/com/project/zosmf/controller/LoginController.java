package com.project.zosmf.controller;

import com.project.zosmf.service.LoginService;
import com.project.zosmf.utils.AuthUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
public class LoginController {
    @Resource
    private LoginService loginService;

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody Map<String, String> account, HttpSession session) {
        if (AuthUtil.notLogin(session)) {
            switch (loginService.login(account, session)) {
                case "unauthorized":
                    return ResponseEntity.status(401).body("unauthorized");
                case "time out":
                    return ResponseEntity.status(502).body("zosmf time out");
            }
        }
        //loginService.setRole(session);
        return ResponseEntity.ok("successful");
    }

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(value = "/logoff", method = RequestMethod.DELETE)
    public ResponseEntity<String> logout(HttpSession session) {
        loginService.logoff(session);
        return ResponseEntity.ok("successful");
    }
}
