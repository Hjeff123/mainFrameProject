package com.project.zosmf.controller;

import com.project.zosmf.service.SortService;
import com.project.zosmf.utils.AuthUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class SortController {
    @Resource
    private SortService sortService;

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(value = "/autoSort", method = RequestMethod.GET)
    public ResponseEntity<String> autoSort(HttpSession session) {
        if (AuthUtil.notLogin(session)) {
            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.ok(sortService.sortTableAndMergeThem(session));
    }

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(value = "/autoSort1", method = RequestMethod.GET)
    public ResponseEntity<String> autoSort1(@RequestParam String input1, @RequestParam String input2, @RequestParam String output, HttpSession session) {
        if (AuthUtil.notLogin(session)) {
            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.ok(sortService.sortTableAndMergeThem1(session, input1, input2, output));
    }

}
