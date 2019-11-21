package com.railwayTicketManager.services;

import com.railwayTicketManager.api.FinancialReqBO;
import com.railwayTicketManager.api.FinancialRspBO;
import com.railwayTicketManager.baseBO.RspPageBO;


/**
 * @Author: meizx
 * @ClassName: FinancialService
 * @Date: 2019/12/5 15:17
 * @E-mail： meizxbj@gmail.com
 * @Description: FinancialService
 * @Version: V1.0.0
 */
public interface FinancialService {
    RspPageBO<FinancialRspBO> query(FinancialReqBO financialReqBO);
}
