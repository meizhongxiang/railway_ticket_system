package com.railwayTicketManager.config.log;


import com.railwayTicketManager.api.SystemLogBO;
import com.railwayTicketManager.api.SystemLogReqBO;
import com.railwayTicketManager.api.SystemLogRspBO;
import com.railwayTicketManager.baseBO.Constants;
import com.railwayTicketManager.baseBO.RspPageBO;
import com.railwayTicketManager.dao.SystemLogDAO;
import com.railwayTicketManager.dao.po.SystemMonitorPO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: meizx
 * @ClassName: SystemLogServiceImpl
 * @Date: 2019/11/30 9:10
 * @E-mail： meizxbj@gmail.com
 * @Description: SystemLogServiceImpl
 * @Version: V1.0.0
 */
@Service
public class SystemLogServiceImpl implements SystemLogService{

    @Autowired
    private SystemLogDAO systemLogDAO;
    /**
     * 保存日志
     */
    @Override
    @Async
    public void save(SystemLogBO systemLogBO) {
        systemLogDAO.save(systemLogBO);
    }

    /**
     * 分页查询日志
     */
    @Override
    public RspPageBO<SystemLogRspBO> selectLogList(SystemLogReqBO systemLogReqBO) {
        RspPageBO<SystemLogRspBO> rsp = new RspPageBO<SystemLogRspBO>();
        SystemMonitorPO systemMonitorPO = new SystemMonitorPO();
        if(systemLogReqBO.getThisDate()!= null && systemLogReqBO.getThiseDate() != null){
            systemMonitorPO.setStartDate(systemLogReqBO.getThisDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            systemMonitorPO.setEndDate(systemLogReqBO.getThiseDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        systemMonitorPO.setSType(systemLogReqBO.getStatus());
        systemMonitorPO.setPage(systemLogReqBO.getPage()==0?0: systemLogReqBO.getPage()*10-10);
        int pcount = systemLogDAO.getCount(systemMonitorPO);
        List<SystemMonitorPO> list = systemLogDAO.getListPage(systemMonitorPO);
        List<SystemLogRspBO> rspList = new ArrayList<>();
        SystemLogRspBO systemLogRspBO = null;
        for(SystemMonitorPO po : list){
            systemLogRspBO =new SystemLogRspBO();
            BeanUtils.copyProperties(po, systemLogRspBO);
            rspList.add(systemLogRspBO);
        }
        rsp.setRspDesc("查询成功");
        rsp.setDataCount(pcount);
        rsp.setRspCode(Constants.RSP_CODE_SUCCESS);
        rsp.setRow(rspList);
        return rsp;
    }

    /**
     * 根据id删除日志
     */
    @Override
    public boolean removeById(Serializable id) {
        return false;
    }
}
