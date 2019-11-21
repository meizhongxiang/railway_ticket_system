package com.railwayTicketManager.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * @Author: meizx
 * @ClassName: TicketReqBO
 * @Date: 2019/12/2 9:33
 * @E-mail： meizxbj@gmail.com
 * @Description: TicketReqBO
 * @Version: V1.0.0
 */
@Data
public class TicketReqBO implements Serializable {
    private int ticketId;
    private String trainNumber;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private LocalDate startDate;
    private String startSite;
    private String endSite;
    @JsonFormat(pattern="HH:mm:ss",timezone = "GMT+8")
    private LocalTime startTime;
    @JsonFormat(pattern="HH:mm:ss",timezone = "GMT+8")
    private LocalTime endTime;
    private int classOne;
    private int classTwo;
    private int priceOne;
    private int priceTwo;
    private String useTime;
    private int status;
    private int page;
    private String name;
    private String pwd;
}
