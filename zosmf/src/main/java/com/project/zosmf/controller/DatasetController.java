package com.project.zosmf.controller;

import com.project.zosmf.service.DatasetService;
import com.project.zosmf.utils.AuthUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class DatasetController {
    @Resource
    private DatasetService datasetService;

    //获取一个数据集的内容（可以为顺序数据集或是分区数据集的一个成员）
    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(value = "/dataset/{name}", method = RequestMethod.GET)
    public ResponseEntity<String> getContent(@PathVariable String name, HttpSession session) {
        if (AuthUtil.notLogin(session)) {
            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.ok(datasetService.getContent(session, name));
    }
}
