package com.railwayTicketManager.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * @Author: meizx
 * @ClassName: FinancialReqBO
 * @Date: 2019/12/5 15:22
 * @E-mail： meizxbj@gmail.com
 * @Description: FinancialReqBO
 * @Version: V1.0.0
 */
@Data
public class FinancialReqBO implements Serializable {
    private String startSite;
    private String endSite;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private LocalDate startDate;
    private int status;
}
