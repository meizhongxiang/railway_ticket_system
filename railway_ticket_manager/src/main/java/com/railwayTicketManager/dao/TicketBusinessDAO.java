package com.railwayTicketManager.dao;

import com.railwayTicketManager.api.TicketRspBO;
import com.railwayTicketManager.dao.po.TicketBusinessPO;
import com.railwayTicketManager.dao.po.TrainDetailPO;

import java.util.List;

/**
 * @Author: meizx
 * @ClassName: TicketBusinessDAO
 * @Date: 2019/12/2 9:42
 * @E-mail： meizxbj@gmail.com
 * @Description: TicketBusinessDAO
 * @Version: V1.0.0
 */
public interface TicketBusinessDAO {
    //添加车次
    int addTicket(TicketBusinessPO ticketBusinessPO);
    //删除车次
    int deleteTicket(TicketBusinessPO ticketBusinessPO);
    int deleteTicketDetail(TicketBusinessPO ticketBusinessPO);
    //修改车次价格
    int updateTicketPrice(TicketBusinessPO ticketBusinessPO);
    TicketBusinessPO getTicketPrice(TicketBusinessPO ticketBusinessPO);
    //修改车次数量
    int updateTicketNum(TrainDetailPO trainDetailPO);
    //分页查询车次
    List<TicketBusinessPO> queryTicket(TicketBusinessPO ticketBusinessPO);
    int countInfo(TicketBusinessPO ticketBusinessPO);
    int countToday(TicketBusinessPO ticketBusinessPO);
    int reduceTicketOne(TicketBusinessPO ticketBusinessPO);
    int reduceTicketTwo(TicketBusinessPO ticketBusinessPO);
    int pariseTicketOne(TicketBusinessPO ticketBusinessPO);
    int pariseTicketTwo(TicketBusinessPO ticketBusinessPO);
}
