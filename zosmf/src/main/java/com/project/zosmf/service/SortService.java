package com.project.zosmf.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
public class SortService {

    @Resource
    private JclService js;

    /**
     * Sort the data in two data sets and put the results into a new data set
     */
    public String sortTableAndMergeThem(HttpSession session) {
            String uid = session.getAttribute("ZOSMF_Account").toString();
            String jcl =
                    "//" +uid +"L1 JOB TEST,NOTIFY=&SYSUID,TIME=2\n" +
                    "//STEP1 EXEC PGM=SORT\n" +
//                    "//SORTIN DD DSN=" +uid + ".DATA1(TEST1),DISP=SHR\n" +
//                    "//       DD DSN=" +uid +".DATA1(TEST2),DISP=SHR\n" +
                    "//SORTIN DD DSN=ST065.DATA(GRADEMIX),DISP=SHR\n" +
                    "//       DD DSN=ST065.DATA(GRADEMIX),DISP=SHR\n" +
                    "//SORTOUT DD DSN=" +uid +".DATA(TEST),DISP=(OLD,,DELETE),\n" +
                    "//     VOL=SER=BYWK00,UNIT=SYSDA\n" +
                    "//SYSOUT DD SYSOUT=*\n" +
                    "//SYSIN DD *\n" +
                    "  SORT FIELDS=(2,5,CH,A)\n" +
                    "  OUTFIL FNAMES=SORTOUT\n" +
                    "/*";
            return js.submitJCL(session, jcl, 102);
    }

    public String sortTableAndMergeThem1(HttpSession session, String input1, String input2, String output){
        String uid = session.getAttribute("ZOSMF_Account").toString();
        String jcl =
                "//" +uid +"L1 JOB TEST,NOTIFY=&SYSUID,TIME=2\n" +
                        "//STEP1 EXEC PGM=SORT\n" +
                        //"//SORTIN DD DSN=ST065.DATA(GRADEMIX),DISP=SHR\n" +
                        "//SORTIN DD DSN=" + input1 + ",DISP=SHR\n" +
                        //"//       DD DSN=ST065.DATA(GRADEMIX),DISP=SHR\n" +
                        "//       DD DSN=" + input2 + ",DISP=SHR\n" +
                        "//SORTOUT DD DSN=" + output + ",DISP=(OLD,,DELETE),\n" +
                        "//     VOL=SER=BYWK00,UNIT=SYSDA\n" +
                        "//SYSOUT DD SYSOUT=*\n" +
                        "//SYSIN DD *\n" +
                        "  SORT FIELDS=(2,5,CH,A)\n" +
                        "  OUTFIL FNAMES=SORTOUT\n" +
                        "/*";
        return js.submitJCL(session, jcl, 102);
    }

}
