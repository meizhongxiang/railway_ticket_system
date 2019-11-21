package com.railwayTicketManager.config.log;

import com.alibaba.fastjson.JSONObject;
import com.railwayTicketManager.api.SystemLogBO;
import com.railwayTicketManager.services.MailService;
import com.railwayTicketManager.utils.IPUtils;
import com.railwayTicketManager.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
/**
 * @Author: meizx
 * @ClassName: SystemLogAspect
 * @Date: 2019/11/30 8:18
 * @E-mail： meizxbj@gmail.com
 * @Description: SystemLogAspect
 * @Version: V1.0.0
 */
@Component
@Slf4j
@Aspect
public class SystemLogAspect {

    private SystemLogBO systemLogBO = new SystemLogBO();
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private MailService mailService;

    @Pointcut("@annotation(com.railwayTicketManager.config.log.SystemLogAnnotat)")
    public void systemLogAspect() { }

     //拦截控制层的操作日志
    @Before(value = "systemLogAspect()")
    public void recordLog(JoinPoint joinPoint) throws Throwable {
        //long beginTime = Instant.now().toEpochMilli();
        systemLogBO.setBeginTime(Instant.now().toEpochMilli());
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        systemLogBO.setActionUrl(request.getRequestURI());
        systemLogBO.setStartTime(LocalDateTime.now());
        systemLogBO.setRequestIp(IPUtils.getUserIP(request));
        systemLogBO.setRequestMethod(request.getMethod());
        systemLogBO.setUa(request.getHeader("user-agent"));
        Object[] args = joinPoint.getArgs();
        systemLogBO.setActionMethod(joinPoint.getSignature().getName());
        systemLogBO.setClassPath(joinPoint.getTarget().getClass().getName());
        systemLogBO.setActionMethod(joinPoint.getSignature().getName());
        //systemLogBO.setFinishTime(LocalDateTime.now());
        systemLogBO.setParams(Arrays.toString(args));
        systemLogBO.setDescription(LogUtils.getControllerMethodDescription(joinPoint));
        //long endTime = Instant.now().toEpochMilli();
        //systemLogBO.setConsumingTime(endTime - beginTime);
    }

     // 返回通知
    @AfterReturning(returning = "ret", pointcut = "systemLogAspect()")
    public void doAfterReturning(Object ret) {
        systemLogBO.setFinishTime(LocalDateTime.now());
        systemLogBO.setConsumingTime(Instant.now().toEpochMilli() - systemLogBO.getBeginTime());
        try{
            String str = JSONObject.toJSONString(ret.toString());
            String subStr=str.substring(str.indexOf("{"),str.indexOf("}")+1);
            String s = subStr.replaceAll("=", ":");
            JSONObject jso = JSONObject.parseObject(s);
            if ("0000".equals(jso.get("rspCode").toString())){
                systemLogBO.setSType(1);
                systemLogBO.setRtParams(str);
                systemLogBO.setExceptDesc("");
                systemLogBO.setExceptDetail("");
            } else {
                systemLogBO.setRtParams(str);
                systemLogBO.setSType(1);
                systemLogBO.setExceptDesc("");
                systemLogBO.setExceptDetail(jso.get("rspDesc").toString());
            }
        }catch (Exception e){
            systemLogBO.setSType(2);
            systemLogBO.setExceptDesc(LogUtils.getStackTrace(e));
            systemLogBO.setExceptDetail("系统监控异常");
            mailService.sendExpectionMail("meizxbj@gmail.com", systemLogBO.getExceptDetail(), "系统监控异常");
        }
        applicationContext.publishEvent(new SystemLogEvent(systemLogBO));
        systemLogBO.setExceptDesc("");
    }

     //异常通知
    @AfterThrowing(pointcut = "systemLogAspect()",throwing = "e")
    public void doAfterThrowable(Throwable e){
        systemLogBO.setSType(2);
        systemLogBO.setExceptDetail(e.getMessage());
        systemLogBO.setExceptDesc(LogUtils.getStackTrace(e));
        mailService.sendExpectionMail("meizxbj@gmail.com", systemLogBO.getExceptDesc(), e.getMessage());
        e.setStackTrace(new StackTraceElement[]{});
        applicationContext.publishEvent(new SystemLogEvent(systemLogBO));
        systemLogBO.setExceptDetail("");
        systemLogBO.setExceptDesc("");
    }
}
