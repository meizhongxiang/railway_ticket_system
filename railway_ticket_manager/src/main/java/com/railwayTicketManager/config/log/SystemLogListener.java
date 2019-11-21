package com.railwayTicketManager.config.log;

import com.railwayTicketManager.api.SystemLogBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author: meizx
 * @ClassName: SystemLogListener
 * @Date: 2019/11/30 9:05
 * @E-mail： meizxbj@gmail.com
 * @Description: SystemLogListener
 * @Version: V1.0.0
 */
@Slf4j
@Component
public class SystemLogListener {

    @Autowired
    private SystemLogService systemLogService;

    @Async
    @Order
    @EventListener(SystemLogEvent.class)
    public void saveSysLog(SystemLogEvent event) {
        SystemLogBO systemLogBO = (SystemLogBO) event.getSource();
        // 保存日志
        systemLogService.save(systemLogBO);
    }
}
