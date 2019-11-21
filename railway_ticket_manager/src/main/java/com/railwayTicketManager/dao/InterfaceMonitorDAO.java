package com.railwayTicketManager.dao;

import com.railwayTicketManager.dao.po.SystemMonitorPO;

import java.util.List;

/**
 * @Author: meizx
 * @ClassName: InterfaceMonitorDAO
 * @Date: 2019/11/27 21:46
 * @E-mail： meizxbj@gmail.com
 * @Description: InterfaceMonitorDAO
 * @Version: V1.0.0
 */
public interface InterfaceMonitorDAO {
    //记录表
    int insertInfo(SystemMonitorPO systemMonitorPO);
    //查询条数
    int selectCount(SystemMonitorPO systemMonitorPO);
    //分页
    List<SystemMonitorPO> getListPage(SystemMonitorPO systemMonitorPO);
}
