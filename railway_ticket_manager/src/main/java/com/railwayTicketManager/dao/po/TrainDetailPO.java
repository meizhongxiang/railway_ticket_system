package com.railwayTicketManager.dao.po;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author: meizx
 * @ClassName: TrainDetailPO
 * @Date: 2019/12/7 21:24
 * @E-mail： meizxbj@gmail.com
 * @Description: TrainDetailPO
 * @Version: V1.0.0
 */
@Data
public class TrainDetailPO {
    private String trainNumber;
    private LocalDate startDate;
    private LocalDate startDateStr;
    private int classOne;
    private int classTwo;
    private int sumOne;
    private int sumTwo;
}
