package com.railwayTicketManager.controller;

import com.railwayTicketManager.api.FinancialReqBO;
import com.railwayTicketManager.api.FinancialRspBO;
import com.railwayTicketManager.api.SystemLogReqBO;
import com.railwayTicketManager.api.SystemLogRspBO;
import com.railwayTicketManager.baseBO.RspPageBO;
import com.railwayTicketManager.config.log.SystemLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: meizx
 * @ClassName: SystemMonitorController
 * @Date: 2019/12/5 15:16
 * @E-mail： meizxbj@gmail.com
 * @Description: SystemMonitorController
 * @Version: V1.0.0
 */
@RestController
@RequestMapping("/proxy")
@Slf4j
public class SystemMonitorController {
    @Autowired
    private SystemLogService systemLogService;

    /**
     * 5000001：分页查询日志
     * */
    @RequestMapping(value = "/5000001", method = RequestMethod.POST)
    @CrossOrigin(origins = "*",maxAge = 3600)
    public RspPageBO<SystemLogRspBO> query(@RequestBody SystemLogReqBO systemLogReqBO) throws Exception{
        RspPageBO<SystemLogRspBO> rsp = null;
        try {
            rsp = systemLogService.selectLogList(systemLogReqBO);
        } catch (Exception e) {
            log.error("========SystemMonitorController---->分页查询日志失败========", e);
        }
        return rsp;
    }
}
