package com.railwayTicketManager.services;

import com.railwayTicketManager.api.OrderDetailReqBO;
import com.railwayTicketManager.api.SaleTicketReqBO;
import com.railwayTicketManager.api.SaleTicketRspBO;
import com.railwayTicketManager.baseBO.RspInfoBO;
import com.railwayTicketManager.baseBO.RspPageBO;
import com.railwayTicketManager.dao.po.SaleTicketPO;

import java.util.List;

/**
 * @Author: meizx
 * @ClassName: SaleTicketService
 * @Date: 2019/12/2 10:18
 * @E-mail： meizxbj@gmail.com
 * @Description: SaleTicketService
 * @Version: V1.0.0
 */
public interface SaleTicketService {
    //添加订单
    RspInfoBO addOrderByUidAndAid(SaleTicketReqBO saleTicketReqBO);
    //分页查询订单
    RspPageBO<SaleTicketRspBO> queryOrderByUidAndAid(OrderDetailReqBO orderDetailReqBO);
    //修改订单状态
    RspInfoBO updateticketStatus(SaleTicketReqBO saleTicketReqBO);
}
