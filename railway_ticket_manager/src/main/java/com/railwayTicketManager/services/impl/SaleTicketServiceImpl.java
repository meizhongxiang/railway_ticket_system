package com.railwayTicketManager.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.railwayTicketManager.api.OrderDetailReqBO;
import com.railwayTicketManager.api.SaleTicketReqBO;
import com.railwayTicketManager.api.SaleTicketRspBO;
import com.railwayTicketManager.baseBO.Constants;
import com.railwayTicketManager.baseBO.RspInfoBO;
import com.railwayTicketManager.baseBO.RspPageBO;
import com.railwayTicketManager.config.RedisClient;
import com.railwayTicketManager.config.UserDefinedException;
import com.railwayTicketManager.config.log.SystemLogAnnotat;
import com.railwayTicketManager.dao.MemberDAO;
import com.railwayTicketManager.dao.SaleTicketDAO;
import com.railwayTicketManager.dao.TicketBusinessDAO;
import com.railwayTicketManager.dao.po.*;
import com.railwayTicketManager.services.MailService;
import com.railwayTicketManager.services.SaleTicketService;
import com.railwayTicketManager.utils.TicketNumberUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: meizx
 * @ClassName: SaleTicketServiceImpl
 * @Date: 2019/12/2 10:18
 * @E-mail： meizxbj@gmail.com
 * @Description: SaleTicketServiceImpl
 * @Version: V1.0.0
 */
@Service
public class SaleTicketServiceImpl implements SaleTicketService {
    @Autowired
    private SaleTicketDAO saleTicketDAO;
    @Autowired
    private TicketBusinessDAO ticketBusinessDAO;
    @Autowired
    private MemberDAO memberDAO;
    @Autowired
    private MailService mailService;
    @Autowired
    private RedisClient redisClient;

    //支付成功或改签后添加订单
    @SystemLogAnnotat(descrption = "支付或改签支付购票")
    @Override
    @Transactional
    public RspInfoBO addOrderByUidAndAid(SaleTicketReqBO saleTicketReqBO) {
        RspInfoBO rsp = new RspInfoBO();
        if("".equals(saleTicketReqBO.getUserAccountId())||saleTicketReqBO.getUserId() <= 0){
            rsp.setRspDesc("账户参数为空");
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            return rsp;
        }
        if("".equals(saleTicketReqBO.getTrainNumber())||saleTicketReqBO.getTrainNumber()==null){
            rsp.setRspDesc("车次参数为空");
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            return rsp;
        }
        if("".equals(saleTicketReqBO.getStartDate())||saleTicketReqBO.getStartDate() == null){
            rsp.setRspDesc("发车日期参数为空");
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            return rsp;
        }
        if("".equals(saleTicketReqBO.getPayPassword()) || saleTicketReqBO.getPayPassword() == null){
            rsp.setRspDesc("支付密码错误");
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            return rsp;
        }
        if(saleTicketReqBO.getTicketClass() < 1){
            rsp.setRspDesc("参数校验失败-坐席");
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            return rsp;
        }

        String objStr = null;
        UserPO userPO = null;
        try {
            objStr = redisClient.get(saleTicketReqBO.getUserId()+"");
            JSONObject jso = JSONObject.parseObject(objStr);
            userPO = JSONObject.toJavaObject(jso, UserPO.class);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        if(!(saleTicketReqBO.getPayPassword().equals(userPO.getPayPassword()))){
            rsp.setRspDesc("用户支付密码错误");
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            return rsp;
        }
        SaleTicketPO saleTicketPO = new SaleTicketPO();
        TicketBusinessPO ticketBusinessPO = new TicketBusinessPO();
        saleTicketPO.setUserId(saleTicketReqBO.getUserId());
        saleTicketPO.setTrainNumber(saleTicketReqBO.getTrainNumber());
        saleTicketPO.setStartDate(saleTicketReqBO.getStartDate());
        saleTicketPO.setBuyDate(LocalDate.now());
        saleTicketPO.setPassenger(saleTicketReqBO.getPassenger());
        saleTicketPO.setTicketClass(saleTicketReqBO.getTicketClass());
        TrainDetailPO trainDetailPO = new TrainDetailPO();
        trainDetailPO.setTrainNumber(saleTicketReqBO.getTrainNumber());
        trainDetailPO.setStartDate(saleTicketReqBO.getStartDate());
        int c = saleTicketDAO.countInfo(saleTicketPO);
        if(c > 0){
            rsp.setRspDesc("您已购当日车次，不能重复购买");
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            return rsp;
        }
        TrainDetailPO trainDetail = saleTicketDAO.getTicketDetail(trainDetailPO);
        if(saleTicketReqBO.getTicketClass() == 1){
            if(trainDetail.getClassOne() <= 0){
                rsp.setRspDesc("当日该车次车票已售空");
                rsp.setRspCode(Constants.RSP_CODE_FAILUR);
                return rsp;
            }
        }else if(saleTicketReqBO.getTicketClass() == 2){
            if(trainDetail.getClassTwo() <= 0){
                rsp.setRspDesc("当日该车次车票已售空");
                rsp.setRspCode(Constants.RSP_CODE_FAILUR);
                return rsp;
            }
        }else{}
        TicketNumberUtil ticketNumberUtil = new TicketNumberUtil();
        String num = ticketNumberUtil.rtNumber(trainDetail,saleTicketReqBO.getTicketClass());
        //座号
        saleTicketPO.setTicketNum(num);
        saleTicketPO.setPassenger(userPO.getUserName());
        saleTicketPO.setStartSite(saleTicketReqBO.getStartSite());
        saleTicketPO.setEndSite(saleTicketReqBO.getEndSite());
        saleTicketPO.setStartTime(saleTicketReqBO.getStartTime());
        saleTicketPO.setEndTime(saleTicketReqBO.getEndTime());
        saleTicketPO.setUseTime(saleTicketReqBO.getUseTime());
        saleTicketPO.setPrice(saleTicketReqBO.getPrice());
        //每100元 一积分
        saleTicketPO.setIntegral(saleTicketReqBO.getPrice());
        //0 退票  1已出票  2待出票  3改签状态  4改签待出票  5改签已出票
        if(saleTicketReqBO.getTicketStatus()==4){
            saleTicketPO.setTicketStatus(4);
        }else{
            saleTicketPO.setTicketStatus(2);
        }
        saleTicketPO.setUserAccountId(saleTicketReqBO.getUserAccountId());
        ticketBusinessPO.setTrainNumber(saleTicketReqBO.getTrainNumber());
        ticketBusinessPO.setStartDate(saleTicketReqBO.getStartDate());
        MemberPO memberPO = new MemberPO();
        memberPO.setIntegral(saleTicketReqBO.getPrice());
        memberPO.setUserId(saleTicketReqBO.getUserId());
        TicketBusinessPO tprice = ticketBusinessDAO.getTicketPrice(ticketBusinessPO);
        MemberPO membe = memberDAO.selectInfo(saleTicketReqBO.getUserId());
        if(saleTicketReqBO.getTicketClass() == 1){
            if(membe.getIntegral() < tprice.getPriceOne()){
                rsp.setRspDesc("余额不足");
                rsp.setRspCode(Constants.RSP_CODE_FAILUR);
                return rsp;
            }
        }else if(saleTicketReqBO.getTicketClass() == 2){
            if(membe.getIntegral() < tprice.getPriceTwo()){
                rsp.setRspDesc("余额不足");
                rsp.setRspCode(Constants.RSP_CODE_FAILUR);
                return rsp;
            }
        }else{}
        if(saleTicketReqBO.getTicketStatus() == 4){
            saleTicketReqBO.setTicketStatus(0);
            RspInfoBO rspp = updateticketStatus(saleTicketReqBO);
            saleTicketReqBO.setTicketStatus(4);
            if("9999".equals(rspp.getRspCode())){
                rsp.setRspDesc("退票失败");
                rsp.setRspCode(Constants.RSP_CODE_FAILUR);
                return rsp;
            }
        }
        if(saleTicketReqBO.getTicketClass() == 1){
            if(saleTicketDAO.addOrderByUidAndAid(saleTicketPO) >0
                    && ticketBusinessDAO.reduceTicketOne(ticketBusinessPO) > 0
                    && memberDAO.reduceIntegral(memberPO) > 0){
                try {
                    redisClient.set(saleTicketPO.getTrainNumber()+saleTicketPO.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),"1");
                } catch (UserDefinedException e) {
                    //e.printStackTrace();
                }
                if(saleTicketPO.getTicketStatus()==4){
                    mailService.sendPayMail(saleTicketReqBO.getUserAccountId(), saleTicketPO, 4);
                    rsp.setRspDesc("改签成功,正在出票");
                    rsp.setRspCode(Constants.RSP_CODE_SUCCESS);
                }else{
                    mailService.sendPayMail(saleTicketPO.getUserAccountId(), saleTicketPO, 2);
                    rsp.setRspDesc("支付成功,正在出票");
                    rsp.setRspCode(Constants.RSP_CODE_SUCCESS);
                }
            }else{
                rsp.setRspDesc("支付失败");
                rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            }
        }else{
            if(saleTicketDAO.addOrderByUidAndAid(saleTicketPO) >0
                    && ticketBusinessDAO.reduceTicketTwo(ticketBusinessPO) > 0
                    && memberDAO.reduceIntegral(memberPO) > 0){
                if(saleTicketPO.getTicketStatus()==4){
                    mailService.sendPayMail(saleTicketReqBO.getUserAccountId(), saleTicketPO, 4);
                    rsp.setRspDesc("改签成功,正在出票");
                    rsp.setRspCode(Constants.RSP_CODE_SUCCESS);
                }else{
                    mailService.sendPayMail(saleTicketPO.getUserAccountId(), saleTicketPO, 2);
                    rsp.setRspDesc("支付成功,正在出票");
                    rsp.setRspCode(Constants.RSP_CODE_SUCCESS);
                }
            }else{
                rsp.setRspDesc("支付失败");
                rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            }
        }
        return rsp;
    }

    //分页查询订单
    @Override
    public RspPageBO<SaleTicketRspBO> queryOrderByUidAndAid(OrderDetailReqBO orderDetailReqBO) {
        RspPageBO<SaleTicketRspBO> rsp = new RspPageBO<SaleTicketRspBO>();
        if("".equals(orderDetailReqBO.getPage()<0)){
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            rsp.setRspDesc("查询信息错误");
            return rsp;
        }
        List<SaleTicketRspBO> rspList = new ArrayList<SaleTicketRspBO>();
        SaleTicketPO saleTicketPO =new SaleTicketPO();
        SaleTicketRspBO rspBO=null;
        saleTicketPO.setUserId(orderDetailReqBO.getUserId());
        saleTicketPO.setUserAccountId(orderDetailReqBO.getUserAccountId());
        /*saleTicketPO.setStartDate(saleTicketReqBO.getStartDate());
        saleTicketPO.setStartSite(saleTicketReqBO.getStartSite());
        saleTicketPO.setEndSite(saleTicketReqBO.getEndSite());*/
        saleTicketPO.setPage(orderDetailReqBO.getPage()==0?0: orderDetailReqBO.getPage()*10-10);

        int count = saleTicketDAO.countToday(saleTicketPO);
        if(count > 0){
            List<SaleTicketPO> list = saleTicketDAO.queryTicket(saleTicketPO);
            for(SaleTicketPO po:list){
                rspBO = new SaleTicketRspBO();
                BeanUtils.copyProperties(po, rspBO);
                rspList.add(rspBO);
            }
            rsp.setRow(rspList);
            rsp.setDataCount(count);
            rsp.setRspCode(Constants.RSP_CODE_SUCCESS);
            rsp.setRspDesc("查询成功");
        }else{
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            rsp.setRspDesc("查询失败");
        }
        return rsp;
    }

    //退票订单状态
    @SystemLogAnnotat(descrption = "用户退票")
    @Override
    @Transactional
    public RspInfoBO updateticketStatus(SaleTicketReqBO saleTicketReqBO) {
        RspInfoBO rsp = new RspInfoBO();
        if("".equals(saleTicketReqBO.getUserAccountId())||saleTicketReqBO.getUserId() <= 0){
            rsp.setRspDesc("账户参数为空");
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            return rsp;
        }
        if("".equals(saleTicketReqBO.getUpdTrainNumber())||saleTicketReqBO.getUpdTrainNumber()==null){
            rsp.setRspDesc("车次参数为空");
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            return rsp;
        }
        if("".equals(saleTicketReqBO.getUpdStartDate())||saleTicketReqBO.getUpdStartDate() == null){
            rsp.setRspDesc("发车日期参数为空");
            rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            return rsp;
        }
        SaleTicketPO saleTicketPO = new SaleTicketPO();
        TicketBusinessPO ticketBusinessPO = new TicketBusinessPO();
        saleTicketPO.setTicketClass(saleTicketReqBO.getUpdTicketClass());
        //saleTicketPO.setTicketNum(saleTicketReqBO.getUpdTicketNum());
        saleTicketPO.setTrainNumber(saleTicketReqBO.getUpdTrainNumber());
        saleTicketPO.setStartDate(saleTicketReqBO.getUpdStartDate());
        saleTicketPO.setPrice(saleTicketReqBO.getUpdPrice());
        saleTicketPO.setTicketStatus(0);
        saleTicketPO.setUserAccountId(saleTicketReqBO.getUserAccountId());
        saleTicketPO.setUserId(saleTicketReqBO.getUserId());
        ticketBusinessPO.setTrainNumber(saleTicketReqBO.getUpdTrainNumber());
        ticketBusinessPO.setStartDate(saleTicketReqBO.getUpdStartDate());
        MemberPO memberPO = new MemberPO();
        memberPO.setUserId(saleTicketReqBO.getUserId());
        memberPO.setIntegral(saleTicketReqBO.getUpdPrice());
        //0 退票  1已出票  2待出票  3改签状态  4改签待出票  5改签已出票
        if(saleTicketReqBO.getTicketStatus() == 0){
            if(saleTicketDAO.updateticketStatus(saleTicketPO) >0
                    && memberDAO.addIntegral(memberPO) >0
                    && (saleTicketPO.getTicketClass()==1?
                    ticketBusinessDAO.pariseTicketOne(ticketBusinessPO):
                    ticketBusinessDAO.pariseTicketTwo(ticketBusinessPO))>0){
                mailService.sendPayMail(saleTicketReqBO.getUserAccountId(), saleTicketPO, 0);
                rsp.setRspDesc("退票成功");
                rsp.setRspCode(Constants.RSP_CODE_SUCCESS);
            }else{
                rsp.setRspDesc("退票失败");
                rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            }
        }/*else if(saleTicketReqBO.getTicketStatus() == 2){
            if(saleTicketDAO.updateticketStatus(saleTicketPO) >0
                    && (saleTicketPO.getTicketClass()==1?
                    ticketBusinessDAO.pariseTicketOne(ticketBusinessPO):
                    ticketBusinessDAO.pariseTicketTwo(ticketBusinessPO))>0){
                mailService.sendPayMail(saleTicketReqBO.getUserAccountId(), saleTicketPO, 2);
            }else{}
        }*//*else if(saleTicketReqBO.getTicketStatus() == 4){
            if(saleTicketDAO.updateticketStatus(saleTicketPO) >0
                    && (saleTicketPO.getTicketClass()==1?
                    ticketBusinessDAO.pariseTicketOne(ticketBusinessPO):
                    ticketBusinessDAO.pariseTicketTwo(ticketBusinessPO))>0){
                mailService.sendPayMail(saleTicketReqBO.getUserAccountId(), saleTicketPO, 4);
                rsp.setRspDesc("改签成功");
                rsp.setRspCode(Constants.RSP_CODE_SUCCESS);
            }else{
                rsp.setRspDesc("改签失败");
                rsp.setRspCode(Constants.RSP_CODE_FAILUR);
            }*/
        else{}
        return rsp;
    }
}
