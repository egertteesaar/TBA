package com.example.TBA.service;

import com.example.TBA.model.UserInfo;
import com.example.TBA.repository.UserInfoRepository;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Calendar;

@Service
public class UserInfoExtracter {


    @Autowired
    private UserInfoRepository userInfoRepository;

    public void extract(HttpServletRequest request){

        String remoteAddr = "";
        String previousURL ="";
        String previousURL2 ="";
        System.out.println("################33");

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
            previousURL = request.getHeader("referer");
            UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
            Browser browser = userAgent.getBrowser();



            UserInfo info = new UserInfo();

            info.setBrowser(String.valueOf(browser));
            info.setIp_address(String.valueOf(remoteAddr));

            java.util.Date utilDate = new java.util.Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(utilDate);
            cal.set(Calendar.MILLISECOND, 0);

            info.setDate(new Timestamp(utilDate.getTime()));

            userInfoRepository.save(info);

            System.out.println(userInfoRepository.lastHour());

        }
        System.out.println("%%%%%%%%%%%%%%%%%%%");
    }
}
