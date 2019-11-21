package com.railwayTicketManager.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: meizx
 * @ClassName: SystemLogRspBO
 * @Date: 2019/12/5 19:16
 * @E-mail： meizxbj@gmail.com
 * @Description: SystemLogRspBO
 * @Version: V1.0.0
 */
@Data
public class SystemLogRspBO implements Serializable {
    private int id;
    private String requestIp;
    private int sType;
    private String description;
    private String actionMethod;
    private String actionUrl;
    private String params;
    private String ua;
    private String classPath;
    private String requestMethod;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime startTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime finishTime;
    private Long consumingTime;
    private String rtParams;
    private String exceptDesc;
    private String exceptDetail;
}
