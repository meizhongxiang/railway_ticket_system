package com.railwayTicketManager.services;

import com.railwayTicketManager.api.ManagerLoginReqBO;
import com.railwayTicketManager.api.TicketReqBO;
import com.railwayTicketManager.api.TicketRspBO;
import com.railwayTicketManager.baseBO.RspInfoBO;
import com.railwayTicketManager.baseBO.RspPageBO;
import com.railwayTicketManager.dao.po.TicketBusinessPO;

import java.util.List;

/**
 * @Author: meizx
 * @ClassName: TicketManagerService
 * @Date: 2019/12/2 9:40
 * @E-mail： meizxbj@gmail.com
 * @Description: TicketManagerService
 * @Version: V1.0.0
 */
public interface TicketManagerService {
    //添加车次
    RspInfoBO addTicket(TicketReqBO ticketReqBO);
    //删除车次
    RspInfoBO deleteTicket(TicketReqBO ticketReqBO);
    //修改车次价格
    RspInfoBO updateTicketPrice(TicketReqBO ticketReqBO);
    //修改车次数量
    RspInfoBO updateTicketNum(TicketReqBO ticketReqBO);
    //分页查询车次
    RspPageBO<TicketRspBO> queryTicket(TicketReqBO ticketReqBO);

    RspInfoBO login(ManagerLoginReqBO managerLoginReqBO);
}
