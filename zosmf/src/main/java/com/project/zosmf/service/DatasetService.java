package com.project.zosmf.service;

import com.project.zosmf.utils.ZosmfUtil;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class DatasetService {

    private static final String datasetApiPath = "/zosmf/restfiles/ds";

    //获取一个数据集的内容（可以为顺序数据集或是分区数据集的一个成员）
    public String getContent(HttpSession session, String name) {
        String content = "";
        try {
            content = ZosmfUtil.go(session, datasetApiPath + "/" + name,
                    HttpMethod.GET,
                    null,
                    null,
                    String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}
