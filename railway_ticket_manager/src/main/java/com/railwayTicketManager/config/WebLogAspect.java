package com.railwayTicketManager.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Author: meizx
 * @ClassName: WebLogAspect
 * @Date: 2019/11/29 19:11
 * @E-mail： meizxbj@gmail.com
 * @Description: 日志
 * @Version: V1.0.0
 */
//@Component
//@Aspect
//@Order(5)
@Slf4j
public class WebLogAspect {
    private ThreadLocal<Long> startTime=new ThreadLocal<>();

    @Pointcut("execution(public * com.railwayTicketManager.services.*.*(..))")
    public void webLog(){ }

    @Before(value = "webLog()")
    public void doBefore(JoinPoint point){
        startTime.set(System.currentTimeMillis());

        log.info("WebLogAspect.doBefore............");
        ServletRequestAttributes attributes=
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        log.info("IP : "+request.getRemoteAddr());
        log.info("URL:" + request.getRequestURL().toString());
        log.info("HTTP_METHOD:" +request.getMethod());
        log.info("CLASS_NAME : " + point.getSignature().getDeclaringTypeName()+"."+point.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(point.getArgs()));
    }


    @AfterReturning(value = "webLog()",returning = "ret")
    public void doAferReturning(Object ret){
        log.info("WebLogAspect.doAfterReturning.............");
        log.info("Resp: " + ret);
        log.info("Spend Time : " + (System.currentTimeMillis()-startTime.get()));
    }
}
