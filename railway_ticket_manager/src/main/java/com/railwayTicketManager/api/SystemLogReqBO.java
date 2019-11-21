package com.railwayTicketManager.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: meizx
 * @ClassName: SystemLogReqBO
 * @Date: 2019/12/5 19:16
 * @E-mail： meizxbj@gmail.com
 * @Description: SystemLogReqBO
 * @Version: V1.0.0
 */
@Data
public class SystemLogReqBO implements Serializable {
    private int status;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime thisDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime thiseDate;
    private int page;
}
