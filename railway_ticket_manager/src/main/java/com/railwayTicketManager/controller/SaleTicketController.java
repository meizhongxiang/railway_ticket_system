package com.railwayTicketManager.controller;

import com.railwayTicketManager.api.OrderDetailReqBO;
import com.railwayTicketManager.api.SaleTicketReqBO;
import com.railwayTicketManager.api.SaleTicketRspBO;
import com.railwayTicketManager.api.TicketReqBO;
import com.railwayTicketManager.baseBO.RspInfoBO;
import com.railwayTicketManager.baseBO.RspPageBO;
import com.railwayTicketManager.services.SaleTicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: meizx
 * @ClassName: SaleTicketController
 * @Date: 2019/12/2 10:16
 * @E-mail： meizxbj@gmail.com
 * @Description: SaleTicketController
 * @Version: V1.0.0
 */
@RestController
@RequestMapping("/proxy")
@Slf4j
public class SaleTicketController {
    @Autowired
    private SaleTicketService saleTicketService;

    /**
     * 3000001：查询订单
     * */
    @RequestMapping(value = "/3000001", method = RequestMethod.POST)
    @CrossOrigin(origins = "*",maxAge = 3600)
    public RspPageBO<SaleTicketRspBO> queryTicket(@RequestBody OrderDetailReqBO orderDetailReqBO) throws Exception{
        RspPageBO<SaleTicketRspBO> rsp = null;
        try {
            rsp = saleTicketService.queryOrderByUidAndAid(orderDetailReqBO);
        } catch (Exception e) {
            log.error("========SaleTicketController---->查票失败========", e);
        }
        return rsp;
    }


    /**
     * 3000002：买改票
     * */
    @RequestMapping(value = "/3000002", method = RequestMethod.POST)
    @CrossOrigin(origins = "*",maxAge = 3600)
    public RspInfoBO addUpdateTicket(@RequestBody SaleTicketReqBO saleTicketReqBO) throws Exception{
        RspInfoBO rsp = null;
        try {
            rsp = saleTicketService.addOrderByUidAndAid(saleTicketReqBO);
        } catch (Exception e) {
            log.error("========SaleTicketController---->买改票失败========", e);
        }
        return rsp;
    }


    /**
     * 3000003：退票
     * */
    @RequestMapping(value = "/3000003", method = RequestMethod.POST)
    @CrossOrigin(origins = "*",maxAge = 3600)
    public RspInfoBO cancelTicket(@RequestBody SaleTicketReqBO saleTicketReqBO) throws Exception{
        RspInfoBO rsp = null;
        try {
            rsp = saleTicketService.updateticketStatus(saleTicketReqBO);
        } catch (Exception e) {
            log.error("========SaleTicketController---->退票失败========", e);
        }
        return rsp;
    }
}
