package com.railwayTicketManager.dao.po;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * @Author: meizx
 * @ClassName: FinancialPO
 * @Date: 2019/12/5 15:24
 * @E-mail： meizxbj@gmail.com
 * @Description: FinancialPO
 * @Version: V1.0.0
 */
@Data
public class FinancialPO {
    private String trainNumber;
    private String startSite;
    private String endSite;
    private LocalTime startTime;
    private LocalTime endTime;
    private int price;
    private int num;
    private LocalDate startDate;
    private int status;
    private int page;
    private String orders;
}
