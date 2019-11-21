package com.railwayTicketManager.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: meizx
 * @ClassName: HttpSerletUtils
 * @Date: 2019/11/30 8:43
 * @E-mail： meizxbj@gmail.com
 * @Description: HttpSerletUtils
 * @Version: V1.0.0
 */
public class IPUtils {

    public final static String ERROR_IP = "127.0.0.1";

    public static String getUserIP(HttpServletRequest request)
    {
        // 优先取 X-Real-IP
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("x-forwarded-for");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
            if ("0:0:0:0:0:0:0:1".equals(ip))
            {
                ip = ERROR_IP;
            }
        }
        if ("unknown".equalsIgnoreCase(ip))
        {
            ip = ERROR_IP;
            return ip;
        }
        int index = ip.indexOf(',');
        if (index >= 0)
        {
            ip = ip.substring(0, index);
        }

        return ip;
    }
}
