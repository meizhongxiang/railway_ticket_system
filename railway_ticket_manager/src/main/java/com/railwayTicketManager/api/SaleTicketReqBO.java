package com.railwayTicketManager.api;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * @Author: meizx
 * @ClassName: SaleTicketReqBO
 * @Date: 2019/12/2 10:03
 * @E-mail： meizxbj@gmail.com
 * @Description: SaleTicketReqBO
 * @Version: V1.0.0
 */
@Data
public class SaleTicketReqBO implements Serializable {
    private int ticketId;
    //@NotNull
    private String trainNumber;
    //@NotNull
    private LocalDate startDate;
    //乘车人
    private String passenger;
    //坐席
    //@NotNull
    private int ticketClass;
    //座号
    private String ticketNum;
    //@NotNull
    private String startSite;
    //@NotNull
    private String endSite;
    //@NotNull
    private LocalTime startTime;
    //@NotNull
    private LocalTime endTime;
    //@NotNull
    private String useTime;
    //@NotNull
    private int price;
    private int integral;
    private int ticketStatus;
    //@NotNull
    private String userAccountId;
   // @NotNull
    private int userId;
    //@NotNull
    private String payPassword;
    private int updTicketClass;
    private String updTicketNum;
    private String updTrainNumber;
    private LocalDate updStartDate;
    private int updPrice;
    private int page;
}
