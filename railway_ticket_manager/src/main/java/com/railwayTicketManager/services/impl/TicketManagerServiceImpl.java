package com.railwayTicketManager.services.impl;

import com.railwayTicketManager.api.ManagerLoginReqBO;
import com.railwayTicketManager.api.TicketReqBO;
import com.railwayTicketManager.api.TicketRspBO;
import com.railwayTicketManager.baseBO.Constants;
import com.railwayTicketManager.baseBO.RspInfoBO;
import com.railwayTicketManager.baseBO.RspPageBO;
import com.railwayTicketManager.config.RedisClient;
import com.railwayTicketManager.config.UserDefinedException;
import com.railwayTicketManager.config.log.SystemLogAnnotat;
import com.railwayTicketManager.dao.SaleTicketDAO;
import com.railwayTicketManager.dao.TicketBusinessDAO;
import com.railwayTicketManager.dao.po.TicketBusinessPO;
import com.railwayTicketManager.dao.po.TrainDetailPO;
import com.railwayTicketManager.services.TicketManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: meizx
 * @ClassName: TicketManagerServiceImpl
 * @Date: 2019/12/2 9:40
 * @E-mail： meizxbj@gmail.com
 * @Description: TicketManagerServiceImpl
 * @Version: V1.0.0
 */
@Service
@Slf4j
public class TicketManagerServiceImpl implements TicketManagerService {
    @Autowired
    private SaleTicketDAO saleTicketDAO;
    @Autowired
    private TicketBusinessDAO ticketBusinessDAO;
    @Autowired
    private RedisClient redisClient;

    @SystemLogAnnotat(descrption = "管理员添加车次")
    @Override
    @Transactional
    public RspInfoBO addTicket(TicketReqBO ticketReqBO) {
        RspInfoBO rsp = new RspInfoBO();
        if(ticketReqBO.getClassOne() < 0 || ticketReqBO.getClassTwo() < 0){
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            rsp.setRspDesc("车票余量不能为负数");
            return rsp;
        }
        if(ticketReqBO.getPriceOne() < 0 || ticketReqBO.getPriceTwo() < 0){
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            rsp.setRspDesc("车票价格不能为负数");
            return rsp;
        }
        TicketBusinessPO ticketBusinessPO = new TicketBusinessPO();
        ticketBusinessPO.setTrainNumber(ticketReqBO.getTrainNumber());
        ticketBusinessPO.setStartDate(ticketReqBO.getStartDate());
        ticketBusinessPO.setStartSite(ticketReqBO.getStartSite());
        ticketBusinessPO.setEndSite(ticketReqBO.getEndSite());
        if(ticketBusinessDAO.countInfo(ticketBusinessPO) > 0){
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            rsp.setRspDesc("车次不能重复添加");
            return rsp;
        }
        ticketBusinessPO.setStartTime(ticketReqBO.getStartTime());
        ticketBusinessPO.setEndTime(ticketReqBO.getEndTime());
        ticketBusinessPO.setClassOne(ticketReqBO.getClassOne());
        ticketBusinessPO.setClassTwo(ticketReqBO.getClassTwo());
        ticketBusinessPO.setPriceOne(ticketReqBO.getPriceOne());
        ticketBusinessPO.setPriceTwo(ticketReqBO.getPriceTwo());
        ticketBusinessPO.setUseTime(ticketReqBO.getUseTime());
        TrainDetailPO trainDetailPO = new TrainDetailPO();
        trainDetailPO.setTrainNumber(ticketBusinessPO.getTrainNumber());
        trainDetailPO.setStartDate(ticketBusinessPO.getStartDate());
        trainDetailPO.setClassOne(ticketBusinessPO.getClassOne());
        trainDetailPO.setClassTwo(ticketBusinessPO.getClassTwo());
        trainDetailPO.setSumOne(ticketBusinessPO.getClassOne());
        trainDetailPO.setSumTwo(ticketBusinessPO.getClassTwo());
        if(ticketBusinessDAO.addTicket(ticketBusinessPO) > 0 && saleTicketDAO.addDetail(trainDetailPO) > 0){
            rsp.setRspCode(Constants.RSP_CODE_SUCCESS);
            rsp.setRspDesc("车次添加成功");
        }else{
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            rsp.setRspDesc("车次添加失败");
        }
        return rsp;
    }

    @SystemLogAnnotat(descrption = "管理员删除车次")
    @Override
    public RspInfoBO deleteTicket(TicketReqBO ticketReqBO) {
        RspInfoBO rsp = new RspInfoBO();
        if("".equals(ticketReqBO.getTrainNumber()) || ticketReqBO.getTrainNumber() == null){
            rsp.setRspDesc("车次不能为空");
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            return rsp;
        }
        if("".equals(ticketReqBO.getStartDate()) || ticketReqBO.getStartDate() == null){
            rsp.setRspDesc("车次日期不能为空");
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            return rsp;
        }
        TrainDetailPO trainDetailPO = new TrainDetailPO();
        trainDetailPO.setTrainNumber(ticketReqBO.getTrainNumber());
        trainDetailPO.setStartDate(ticketReqBO.getStartDate());
        TrainDetailPO po = saleTicketDAO.getTicketDetail(trainDetailPO);
        if(po.getClassOne() == po.getSumOne() && po.getClassTwo()== po.getSumTwo()){
            TicketBusinessPO ticketBusinessPO = new TicketBusinessPO();
            ticketBusinessPO.setTrainNumber(ticketReqBO.getTrainNumber());
            ticketBusinessPO.setStartDate(ticketReqBO.getStartDate());
            if(ticketBusinessDAO.deleteTicket(ticketBusinessPO) > 0 && ticketBusinessDAO.deleteTicketDetail(ticketBusinessPO) > 0){
                rsp.setRspDesc("车次删除成功");
                rsp.setRspCode(Constants.RSP_CODE_SUCCESS);
            }else{
                rsp.setRspDesc("车次删除失败");
                rsp.setRspCode(Constants.RSP_CODE_FAILUR);

            }
        }else{
            rsp.setRspDesc("该车次日期已有票售出,不能删除该车次");
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
        }
        return rsp;
    }

    @SystemLogAnnotat(descrption = "管理员修改票价")
    @Override
    public RspInfoBO updateTicketPrice(TicketReqBO ticketReqBO) {
        RspInfoBO rsp = new RspInfoBO();
        if("".equals(ticketReqBO.getTrainNumber()) || ticketReqBO.getTrainNumber() == null){
            rsp.setRspDesc("车次不能为空");
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            return rsp;
        }
        if("".equals(ticketReqBO.getPriceOne())  || ticketReqBO.getPriceOne() <=0){
            rsp.setRspDesc("车票价格不能为空");
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            return rsp;
        }
        if("".equals(ticketReqBO.getPriceTwo())  || ticketReqBO.getPriceTwo() <=0){
            rsp.setRspDesc("车票价格不能为空");
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            return rsp;
        }
        TicketBusinessPO ticketBusinessPO = new TicketBusinessPO();
        ticketBusinessPO.setTrainNumber(ticketReqBO.getTrainNumber());
        ticketBusinessPO.setPriceOne(ticketReqBO.getPriceOne());
        ticketBusinessPO.setPriceTwo(ticketReqBO.getPriceTwo());
        ticketBusinessPO.setStartDate(ticketReqBO.getStartDate());
        if(ticketBusinessDAO.updateTicketPrice(ticketBusinessPO) > 0){
            rsp.setRspDesc("车次票价修改成功");
            rsp.setRspCode(Constants.RSP_CODE_SUCCESS);
        }else{
            rsp.setRspDesc("车次票价修改失败");
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
        }
        return rsp;
    }

    @SystemLogAnnotat(descrption = "管理员修改余量")
    @Override
    public RspInfoBO updateTicketNum(TicketReqBO ticketReqBO) {
        RspInfoBO rsp = new RspInfoBO();
        if("".equals(ticketReqBO.getTrainNumber()) || ticketReqBO.getTrainNumber() == null){
            rsp.setRspDesc("车次不能为空");
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            return rsp;
        }
        if("".equals(ticketReqBO.getClassOne())  || ticketReqBO.getClassOne() < 0){
            rsp.setRspDesc("车票容量不能为空");
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            return rsp;
        }
        if("".equals(ticketReqBO.getClassTwo())  || ticketReqBO.getClassTwo() < 0){
            rsp.setRspDesc("车票容量不能为空");
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            return rsp;
        }
        TrainDetailPO trainDetailPO = new TrainDetailPO();
        trainDetailPO.setTrainNumber(ticketReqBO.getTrainNumber());
        trainDetailPO.setClassOne(ticketReqBO.getClassOne());
        trainDetailPO.setClassTwo(ticketReqBO.getClassTwo());
        trainDetailPO.setStartDate(ticketReqBO.getStartDate());
        if(ticketBusinessDAO.updateTicketNum(trainDetailPO) > 0){
            rsp.setRspDesc("车次容量修改成功");
            rsp.setRspCode(Constants.RSP_CODE_SUCCESS);
        }else{
            rsp.setRspDesc("车次容量修改失败");
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
        }
        return rsp;
    }

    //用户、管理员 查票
    @Override
    public RspPageBO<TicketRspBO> queryTicket(TicketReqBO ticketReqBO) {
        RspPageBO<TicketRspBO> rsp = new RspPageBO<TicketRspBO>();
        if("".equals(ticketReqBO.getPage()<0)){
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            rsp.setRspDesc("查询信息错误");
            return rsp;
        }
        List<TicketRspBO> rspList = new ArrayList<TicketRspBO>();
        TicketBusinessPO ticketBusinessPO =new TicketBusinessPO();
        TicketRspBO rspBO=null;
        ticketBusinessPO.setStartDatestr(ticketReqBO.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        ticketBusinessPO.setStartSite(ticketReqBO.getStartSite());
        ticketBusinessPO.setEndSite(ticketReqBO.getEndSite());
        ticketBusinessPO.setPage(ticketReqBO.getPage()==0?0: ticketReqBO.getPage()*10-10);
        if(ticketReqBO.getStatus() == 0){
            ticketBusinessPO.setOrders("endTime");
        }else if(ticketReqBO.getStatus() == 1){
            ticketBusinessPO.setOrders("startTime");
        }else if(ticketReqBO.getStatus() == 2){
            ticketBusinessPO.setOrders("priceTwo");
        }else{
            ticketBusinessPO.setOrders("startTime");
        }

        int count = ticketBusinessDAO.countToday(ticketBusinessPO);
        if(count > 0){
            List<TicketBusinessPO> list = ticketBusinessDAO.queryTicket(ticketBusinessPO);
            for(TicketBusinessPO po:list){
                rspBO = new TicketRspBO();
                BeanUtils.copyProperties(po,rspBO);
                rspList.add(rspBO);
            }
            rsp.setRow(rspList);
            rsp.setStartDate(ticketReqBO.getStartDate());
            rsp.setDataCount(count);
            rsp.setRspCode(Constants.RSP_CODE_SUCCESS);
            rsp.setRspDesc("查询成功");
        }else{
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            rsp.setRspDesc("当日暂无该区间车次");
        }
        return rsp;
    }

    @SystemLogAnnotat(descrption = "管理员登录")
    @Override
    public RspInfoBO login(ManagerLoginReqBO managerLoginReqBO) {
        RspInfoBO rsp = new RspInfoBO();
        if("".equals(managerLoginReqBO.getAccountId())|| managerLoginReqBO.getAccountId()==null){
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            rsp.setRspDesc("登录失败，管理员账号错误");
            return rsp;
        }
        if("".equals(managerLoginReqBO.getUserPassword())|| managerLoginReqBO.getUserPassword()==null){
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            rsp.setRspDesc("登录失败，管理员密码错误");
            return rsp;
        }
        String m = null;
        try {
            m = redisClient.get(managerLoginReqBO.getAccountId());
        } catch (Exception e) {
            log.debug("=======账号："+managerLoginReqBO.getAccountId()+"==m=="+m);
            e.printStackTrace();
        }
        if("".equals(m) || m==null){
            rsp.setRspCode("8865");
            rsp.setRspDesc("登录失败，管理员密码错误");
        }else{
            if(m.equals(managerLoginReqBO.getUserPassword())){
                rsp.setRspCode(Constants.RSP_CODE_SUCCESS);
                rsp.setRspDesc("管理员登录成功");
            }else{
                rsp.setRspCode("8865");
                rsp.setRspDesc("登录失败，管理员密码错误");
            }
        }
        return rsp;
    }
}
