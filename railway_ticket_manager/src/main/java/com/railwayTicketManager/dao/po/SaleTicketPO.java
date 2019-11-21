package com.railwayTicketManager.dao.po;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * @Author: meizx
 * @ClassName: SaleTicketPO
 * @Date: 2019/12/2 9:56
 * @E-mail： meizxbj@gmail.com
 * @Description: SaleTicketPO
 * @Version: V1.0.0
 */
@Data
public class SaleTicketPO implements Serializable {
    private int ticketId;
    private String trainNumber;
    private LocalDate startDate;
    //乘车人
    private String passenger;
    //坐席
    private int ticketClass;
    //座号
    private String ticketNum;
    private String startSite;
    private String endSite;
    private LocalTime startTime;
    private LocalTime endTime;
    private String useTime;
    private int price;
    private int integral;
    private int ticketStatus;
    private String userAccountId;
    private int userId;
    private int page;
    private LocalDate buyDate;
}
