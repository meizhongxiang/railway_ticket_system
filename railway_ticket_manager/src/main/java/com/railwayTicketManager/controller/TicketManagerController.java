package com.railwayTicketManager.controller;

import com.railwayTicketManager.api.*;
import com.railwayTicketManager.baseBO.RspInfoBO;
import com.railwayTicketManager.baseBO.RspPageBO;
import com.railwayTicketManager.services.TicketManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: meizx
 * @ClassName: TicketManagerController
 * @Date: 2019/12/2 9:39
 * @E-mail： meizxbj@gmail.com
 * @Description: TicketManagerController
 * @Version: V1.0.0
 */
@RestController
@RequestMapping("/proxy")
@Slf4j
public class TicketManagerController {
    @Autowired
    private TicketManagerService ticketManagerService;

    /**
     * 2000001：添加车次
     * */
    @RequestMapping(value = "/2000001", method = RequestMethod.POST)
    @CrossOrigin(origins = "*",maxAge = 3600)
    public RspInfoBO addTrain(@RequestBody TicketReqBO ticketReqBO) throws Exception{
        RspInfoBO rsp = null;
        try {
            rsp = ticketManagerService.addTicket(ticketReqBO);
        } catch (Exception e) {
            log.error("========TicketManagerController---->添加失败========", e);
        }
        return rsp;
    }

    /**
     * 2000002：删除车次
     * */
    @RequestMapping(value = "/2000002", method = RequestMethod.POST)
    @CrossOrigin(origins = "*",maxAge = 3600)
    public RspInfoBO deleteTrain(@RequestBody TicketReqBO ticketReqBO) throws Exception{
        RspInfoBO rsp = null;
        try {
            rsp = ticketManagerService.deleteTicket(ticketReqBO);
        } catch (Exception e) {
            log.error("========TicketManagerController---->删除车次失败========", e);
        }
        return rsp;
    }


    /**
     * 2000003：修改票价
     * */
    @RequestMapping(value = "/2000003", method = RequestMethod.POST)
    @CrossOrigin(origins = "*",maxAge = 3600)
    public RspInfoBO updateTrainPrice(@RequestBody TicketReqBO ticketReqBO) throws Exception{
        RspInfoBO rsp = null;
        try {
            rsp = ticketManagerService.updateTicketPrice(ticketReqBO);
        } catch (Exception e) {
            log.error("========TicketManagerController---->车次修改票价失败========", e);
        }
        return rsp;
    }


    /**
     * 2000004：修改票量
     * */
    @RequestMapping(value = "/2000004", method = RequestMethod.POST)
    @CrossOrigin(origins = "*",maxAge = 3600)
    public RspInfoBO updateTrainNum(@RequestBody TicketReqBO ticketReqBO) throws Exception{
        RspInfoBO rsp = null;
        try {
            rsp = ticketManagerService.updateTicketNum(ticketReqBO);
        } catch (Exception e) {
            log.error("========TicketManagerController---->车次修改票量失败========", e);
        }
        return rsp;
    }


    /**
     * 2000005：分页查询车次
     * */
    @RequestMapping(value = "/2000005", method = RequestMethod.POST)
    @CrossOrigin(origins = "*",maxAge = 3600)
    public RspPageBO<TicketRspBO> queryTrain(@RequestBody TicketReqBO ticketReqBO) throws Exception{
        RspPageBO<TicketRspBO> rsp = null;
        try {
            rsp = ticketManagerService.queryTicket(ticketReqBO);
        } catch (Exception e) {
            log.error("========TicketManagerController---->车次分页查询失败========", e);
        }
        return rsp;
    }

    /**
     * 2000006：管理员密码登录
     * */
    @RequestMapping(value = "/2000006", method = RequestMethod.POST)
    @CrossOrigin(origins = "*",maxAge = 3600)
    public RspInfoBO loginByPwd(@RequestBody ManagerLoginReqBO managerLoginReqBO) throws Exception{
        RspInfoBO rsp = null;
        try {
            rsp = ticketManagerService.login(managerLoginReqBO);
        } catch (Exception e) {
            log.error("========TicketManagerController---->管理员密码登录失败========", e);
        }
        return rsp;
    }
}
