package com.railwayTicketManager.services.impl;

import com.railwayTicketManager.api.FinancialReqBO;
import com.railwayTicketManager.api.FinancialRspBO;
import com.railwayTicketManager.baseBO.Constants;
import com.railwayTicketManager.baseBO.RspPageBO;
import com.railwayTicketManager.dao.SaleTicketDAO;
import com.railwayTicketManager.dao.po.FinancialPO;
import com.railwayTicketManager.services.FinancialService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: meizx
 * @ClassName: FinancialServiceImpl
 * @Date: 2019/12/5 15:17
 * @E-mail： meizxbj@gmail.com
 * @Description: FinancialServiceImpl
 * @Version: V1.0.0
 */
@Service
public class FinancialServiceImpl implements FinancialService {
    @Autowired
    private SaleTicketDAO saleTicketDAO;

    @Override
    public RspPageBO<FinancialRspBO> query(FinancialReqBO financialReqBO) {
        RspPageBO<FinancialRspBO> rsp = new RspPageBO<>();
        FinancialPO financialPO = new FinancialPO();
        financialPO.setEndSite(financialReqBO.getEndSite());
        financialPO.setStartSite(financialReqBO.getStartSite());
        financialPO.setStartDate(financialReqBO.getStartDate());
        if(financialReqBO.getStatus() ==1){
            financialPO.setOrders("num");
        }else if(financialReqBO.getStatus() ==2){
            financialPO.setOrders("price");
        }else{
            financialPO.setOrders("trainNumber");
        }
        FinancialRspBO financialRspBO = null;
        int pcount = saleTicketDAO.getCount(financialPO);
        if(pcount > 0){
            List<FinancialRspBO> rspList = new ArrayList<>();
            List<FinancialPO> list = saleTicketDAO.query(financialPO);
            for(FinancialPO po : list){
                financialRspBO = new FinancialRspBO();
                financialRspBO.setNum(po.getNum());
                financialRspBO.setPrice(po.getPrice());
                financialRspBO.setTrainNumber(po.getTrainNumber());
                financialRspBO.setSite(po.getStartSite()+"-"+po.getEndSite());
                financialRspBO.setStartTime(po.getStartTime());
                financialRspBO.setEndTime(po.getEndTime());
                //BeanUtils.copyProperties(po, financialRspBO);
                rspList.add(financialRspBO);
            }
            rsp.setDataCount(pcount);
            rsp.setRow(rspList);
            rsp.setStartDate(financialReqBO.getStartDate());
            rsp.setRspCode(Constants.RSP_CODE_SUCCESS);
            rsp.setRspDesc("查询成功");
        }else{
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            rsp.setRspDesc("暂无当日信息");
        }
        return rsp;
    }
}
