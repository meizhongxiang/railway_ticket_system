package com.railwayTicketManager.config.log;

import com.railwayTicketManager.api.SystemLogBO;
import org.springframework.context.ApplicationEvent;

/**
 * @Author: meizx
 * @ClassName: SystemLogEvent
 * @Date: 2019/11/30 9:03
 * @E-mail： meizxbj@gmail.com
 * @Description: SystemLogEvent
 * @Version: V1.0.0
 */
public class SystemLogEvent extends ApplicationEvent {

    public SystemLogEvent(SystemLogBO source) {
        super(source);
    }
}
