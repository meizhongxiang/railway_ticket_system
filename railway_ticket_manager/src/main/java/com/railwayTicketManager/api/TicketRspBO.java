package com.railwayTicketManager.api;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

/**
 * @Author: meizx
 * @ClassName: TicketRspBO
 * @Date: 2019/12/2 9:33
 * @E-mail： meizxbj@gmail.com
 * @Description: TicketRspBO
 * @Version: V1.0.0
 */
@Data
public class TicketRspBO implements Serializable {
    private String trainNumber;
    private Date startDate;
    private String startSite;
    private String endSite;
    private LocalTime startTime;
    private LocalTime endTime;
    private int classOne;
    private int classTwo;
    private int priceOne;
    private int priceTwo;
    private String rspCode;
    private String rspDesc;
    private String useTime;
}
