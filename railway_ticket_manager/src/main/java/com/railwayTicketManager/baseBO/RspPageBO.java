package com.railwayTicketManager.baseBO;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * @Author: Qidian
 * @ClassName: RspPageBO
 * @Date: 2019-06-27 10:43
 * @E-mail： qidian@jiamsh.com
 * @Description:批量查询
 * @Version: V1.0.0
 */
@Data
public class RspPageBO<T> implements Serializable {
    private List<T> row;
    private String rspDesc;
    private String rspCode;
    private int pageSize;
    private int dataCount;
    private LocalDate startDate;
}
