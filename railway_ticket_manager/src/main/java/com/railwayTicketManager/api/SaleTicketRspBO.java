package com.railwayTicketManager.api;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * @Author: meizx
 * @ClassName: SaleTicketRspBO
 * @Date: 2019/12/2 10:03
 * @E-mail： meizxbj@gmail.com
 * @Description: SaleTicketRspBO
 * @Version: V1.0.0
 */
@Data
public class SaleTicketRspBO {
    private int ticketId;
    private String trainNumber;
    //乘车人
    private String passenger;
    //坐席
    private int ticketClass;
    //座号
    private String ticketNum;
    private String startSite;
    private LocalDate startDate;
    private String endSite;
    private LocalTime startTime;
    private LocalTime endTime;
    private String useTime;
    private int price;
    private int ticketStatus;
    private String userAccountId;
    private int userId;
    private String rspCode;
    private String rspDesc;
    private LocalDate buyDate;
}
