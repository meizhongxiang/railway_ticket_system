package com.railwayTicketManager.controller;

import com.railwayTicketManager.api.FinancialReqBO;
import com.railwayTicketManager.api.FinancialRspBO;
import com.railwayTicketManager.api.TicketReqBO;
import com.railwayTicketManager.api.TicketRspBO;
import com.railwayTicketManager.baseBO.RspPageBO;
import com.railwayTicketManager.services.FinancialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: meizx
 * @ClassName: FinancialAnalysisController
 * @Date: 2019/12/5 15:15
 * @E-mail： meizxbj@gmail.com
 * @Description: FinancialAnalysisController
 * @Version: V1.0.0
 */
@RestController
@RequestMapping("/proxy")
@Slf4j
public class FinancialAnalysisController {
    @Autowired
    private FinancialService financialService;

    /**
     * 4000001：按天分页查询车次运营情况
     * */
    @RequestMapping(value = "/4000001", method = RequestMethod.POST)
    @CrossOrigin(origins = "*",maxAge = 3600)
    public RspPageBO<FinancialRspBO> query(@RequestBody FinancialReqBO financialReqBO) throws Exception{
        RspPageBO<FinancialRspBO> rsp = null;
        try {
            rsp = financialService.query(financialReqBO);
        } catch (Exception e) {
            log.error("========FinancialAnalysisController---->按天分页查询车次运营情况失败========", e);
        }
        return rsp;
    }
}
