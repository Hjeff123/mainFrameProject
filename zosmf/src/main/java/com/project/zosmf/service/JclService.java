package com.project.zosmf.service;
import com.project.zosmf.entity.JobInfo;
import com.project.zosmf.utils.ZosmfUtil;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;

@Service
public class JclService {

    //向主机服务器提交JCL并且获取返回信息
    @SuppressWarnings("SameParameterValue")
    String submitJCL(HttpSession session, String jcl, int id) {
        JobInfo jobInfo = ZosmfUtil.go(session, "/zosmf/restjobs/jobs", HttpMethod.PUT, jcl, null, JobInfo.class);
        if (jobInfo != null && ZosmfUtil.isReady(session, "/zosmf/restjobs/jobs/" + jobInfo.getJobName() + "/" + jobInfo.getJobId(), 20)) {
            String outputPath = "/zosmf/restjobs/jobs/" + jobInfo.getJobName() + "/" + jobInfo.getJobId() + "/files/" + id + "/records";
            try {
                return ZosmfUtil.go(session, outputPath, HttpMethod.GET, null, null, String.class);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
        return null;
    }
}
