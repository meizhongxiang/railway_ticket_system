package com.railwayTicketManager.dao;

import com.railwayTicketManager.api.SystemLogBO;
import com.railwayTicketManager.dao.po.SystemMonitorPO;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: meizx
 * @ClassName: SystemLogDAO
 * @Date: 2019/11/30 9:41
 * @E-mail： meizxbj@gmail.com
 * @Description: SystemLogDAO
 * @Version: V1.0.0
 */
public interface SystemLogDAO {
    //保存日志
    boolean save(SystemLogBO systemLogBO);

    //分页查询日志
    List<SystemMonitorPO> getListPage(SystemMonitorPO systemMonitorPO);
    int getCount(SystemMonitorPO systemMonitorPO);

    //根据id删除日志
    boolean removeById(Serializable id);
}
