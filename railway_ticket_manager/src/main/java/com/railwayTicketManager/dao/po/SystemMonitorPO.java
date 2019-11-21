package com.railwayTicketManager.dao.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: meizx
 * @ClassName: SystemMonitorPO
 * @Date: 2019/11/27 21:38
 * @E-mail： meizxbj@gmail.com
 * @Description: SystemMonitorPO
 * @Version: V1.0.0
 */
@Data
public class SystemMonitorPO implements Serializable {
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
    private String startDate;
    private String endDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime startTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime finishTime;
    private Long consumingTime;
    private String rtParams;
    private String exceptDesc;
    private String exceptDetail;
    private int page;
   /* //主键
    private Integer id;
    //接口名称
    private String interfaceName;
    //业务类别
    private String ywClass;
    //业务信息
    private String ywInfo;
    //请求开始时间
    private Long startTime;
    //请求结束时间
    private Long endTime;
    //响应用时ms
    private Long useTime;
    //错误日志
    private String errorText;
    //响应状态
    private String rtStatus;
    //系统状态 0异常  1正常
    private Integer syStatus;
    private Integer page;*/

}
