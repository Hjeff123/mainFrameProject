package com.project.zosmf.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
public class SortService {

    @Resource
    private JclService jclService;

    //jcl由后端写死，前端直接点击提交
    public String sortTableAndMergeThem(HttpSession session) {
            String uid = session.getAttribute("ZOSMF_Account").toString();
            String jcl =
                    "//" +uid +"L1 JOB TEST,NOTIFY=&SYSUID,TIME=2\n" +
                "//STEP1 EXEC PGM=SORT\n" +
                "//SORTIN DD DSN=ST034.DATA1(TEST1),DISP=SHR\n" +
                "//       DD DSN=ST034.DATA1(TEST2),DISP=SHR\n" +
                "//SORTOUT DD SYSOUT=*\n" +
                "//SYSOUT DD SYSOUT=*\n" +
                "//SYSIN DD *\n" +
                "  SORT FIELDS=(2,5,CH,A)\n" +
                "/*";
            return jclService.submitJCL(session, jcl, 102);
    }

    //接收前端的输入框信息，进行字符串拼接
    public String sortTableAndMergeThem1(HttpSession session, String input1, String input2, String output){
        String uid = session.getAttribute("ZOSMF_Account").toString();
        String jcl =
                "//" +uid +"L1 JOB TEST,NOTIFY=&SYSUID,TIME=2\n" +
                        "//STEP1 EXEC PGM=SORT\n" +
                        "//SORTIN DD DSN=" + input1 + ",DISP=SHR\n" +
                        "//       DD DSN=" + input2 + ",DISP=SHR\n" +
                        "//SORTOUT DD DSN=" + output + ",DISP=(OLD,,DELETE),\n" +
                        "//     VOL=SER=BYWK00,UNIT=SYSDA\n" +
                        "//SYSOUT DD SYSOUT=*\n" +
                        "//SYSIN DD *\n" +
                        "  SORT FIELDS=(2,5,CH,A)\n" +
                        "  OUTFIL FNAMES=SORTOUT\n" +
                        "/*";
        return jclService.submitJCL(session, jcl, 102);
    }

    //接收前端的jcl并直接提交
    public String sortTableAndMergeThem2(HttpSession session, String jcl){
        return jclService.submitJCL(session, jcl, 102);
    }

}
