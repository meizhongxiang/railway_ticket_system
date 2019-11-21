package com.railwayTicketManager.api;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

/**
 * @Author: meizx
 * @ClassName: FinancialRspBO
 * @Date: 2019/12/5 15:22
 * @E-mail： meizxbj@gmail.com
 * @Description: FinancialRspBO
 * @Version: V1.0.0
 */
@Data
public class FinancialRspBO {
    private String site;
    private Date startDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String trainNumber;
    private int price;
    private int num;
}
