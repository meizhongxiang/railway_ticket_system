package com.railwayTicketManager.dao.po;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * @Author: meizx
 * @ClassName: TicketBusinessPO
 * @Date: 2019/12/2 9:53
 * @E-mail： meizxbj@gmail.com
 * @Description: TicketBusinessPO
 * @Version: V1.0.0
 */
@Data
public class TicketBusinessPO implements Serializable {
    private int ticketId;
    private String trainNumber;
    private String startDatestr;
    private LocalDate startDate;
    private String startSite;
    private String endSite;
    private LocalTime startTime;
    private LocalTime endTime;
    private int classOne;
    private int classTwo;
    private int priceOne;
    private int priceTwo;
    private String useTime;
    //是否已售票
    //private int status;

    private int page;
    private String orders;
}
