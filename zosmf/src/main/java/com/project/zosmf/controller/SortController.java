package com.project.zosmf.controller;

import com.project.zosmf.service.SortService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class SortController {
    @Resource
    private SortService sortService;

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(value = "/autoSort", method = RequestMethod.GET)
    public String autoSort(HttpSession session) {
        return sortService.sortTableAndMergeThem(session);
    }

}
