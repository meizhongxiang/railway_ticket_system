package com.railwayTicketManager.api;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author: meizx
 * @ClassName: SystemLogBO
 * @Date: 2019/11/30 8:10
 * @E-mail： meizxbj@gmail.com
 * @Description: SystemLogBO
 * @Version: V1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SystemLogBO implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 操作IP
     */
    private String requestIp;

    /**
     * 操作类型 1 操作记录2异常记录
     */
    private Integer sType;

    /**
     * 操作人ID
     */
    private String userName;

    /**
     * 操作描述
     */
    private String description;

    /**
     * 请求方法
     */
    private String actionMethod;

    /**
     * 请求url
     */
    private String actionUrl;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 返回参数
     */
    private String rtParams;

    /**
     * 浏览器
     */
    private String ua;

    /**
     * 类路径
     */
    private String classPath;

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 开始时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime startTime;

    /**
     * 完成时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime finishTime;

    /**
     * 消耗时间
     */
    private Long consumingTime;

    /**
     * 异常详情信息 堆栈信息
     */
    private String exceptDetail;

    /**
     * 异常描述 e.getMessage
     */
    private String exceptDesc;

    /**
     * 分页参数
     */
    private int page;
    private long beginTime;
}
