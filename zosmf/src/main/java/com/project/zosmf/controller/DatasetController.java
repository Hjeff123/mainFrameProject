package com.project.zosmf.controller;

import com.project.zosmf.service.DatasetService;
import com.project.zosmf.utils.AuthUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class DatasetController {
    @Resource
    private DatasetService datasetService;

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(value = "/dataset", method = RequestMethod.GET)
    public ResponseEntity<List<Map<String, String>>> getDatasetList(@RequestParam String pattern, HttpSession session) {
        if (AuthUtil.notLogin(session)) {
            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.ok(datasetService.getDatasetList(session, pattern));
    }

    /**
     * get content of a sequential dataset or a partitioned dataset member
     */
    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(value = "/dataset/{name}", method = RequestMethod.GET)
    public ResponseEntity<String> getContent(@PathVariable String name, HttpSession session) {
        if (AuthUtil.notLogin(session)) {
            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.ok(datasetService.getContent(session, name));
    }
}
