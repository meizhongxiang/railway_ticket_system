package com.railwayTicketManager.config.log;

import java.lang.annotation.*;

/**
 * @Author: meizx
 * @ClassName: SystemLogAnnotat
 * @Date: 2019/11/30 8:16
 * @E-mail： meizxbj@gmail.com
 * @Description: SystemLogAnnotat
 * @Version: V1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface SystemLogAnnotat {
    /**
     * 接口描述
     * */
    String descrption() default "" ;
}
