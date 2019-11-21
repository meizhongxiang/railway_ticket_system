package com.railwayTicketManager.config.log;

import com.railwayTicketManager.api.SystemLogBO;
import com.railwayTicketManager.api.SystemLogReqBO;
import com.railwayTicketManager.api.SystemLogRspBO;
import com.railwayTicketManager.baseBO.RspPageBO;
import org.springframework.data.domain.Page;

import java.io.Serializable;

/**
 * @Author: meizx
 * @ClassName: SystemLogService
 * @Date: 2019/11/30 9:08
 * @E-mail： meizxbj@gmail.com
 * @Description: SystemLogService
 * @Version: V1.0.0
 */
public interface SystemLogService {

    //保存日志
    void save(SystemLogBO systemLogBO);

    //分页查询日志
    RspPageBO<SystemLogRspBO> selectLogList(SystemLogReqBO systemLogReqBO);

    //根据id删除日志
    boolean removeById(Serializable id);
}
