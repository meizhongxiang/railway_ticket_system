package com.railwayTicketManager.dao;

import com.railwayTicketManager.dao.po.FinancialPO;
import com.railwayTicketManager.dao.po.SaleTicketPO;
import com.railwayTicketManager.dao.po.TrainDetailPO;

import java.util.List;

/**
 * @Author: meizx
 * @ClassName: SaleTicketDAO
 * @Date: 2019/12/2 9:43
 * @E-mail： meizxbj@gmail.com
 * @Description: SaleTicketDAO
 * @Version: V1.0.0
 */
public interface SaleTicketDAO {
    //添加订单
    int addOrderByUidAndAid(SaleTicketPO saleTicketPO);
    //分页查询订单
    List<SaleTicketPO> queryOrderByUidAndAid(SaleTicketPO saleTicketPO);
    //修改订单状态
    int updateticketStatus(SaleTicketPO saleTicketPO);

    int countToday(SaleTicketPO saleTicketPO);
    int getCount(FinancialPO FinancialPO);

    List<SaleTicketPO> queryTicket(SaleTicketPO saleTicketPO);

    List<FinancialPO> query(FinancialPO FinancialPO);

    int reduceTicketOne(TrainDetailPO trainDetailPO);
    int reduceTicketTwo(TrainDetailPO trainDetailPO);
    int addDetail(TrainDetailPO trainDetailPO);
    TrainDetailPO getTicketDetail(TrainDetailPO trainDetailPO);
    int countInfo(SaleTicketPO saleTicketPO);
}
