package com.railwayTicketManager.utils;

import com.railwayTicketManager.config.log.SystemLogAnnotat;
import org.aspectj.lang.JoinPoint;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
/**
 * @Author: meizx
 * @ClassName: LogUtils
 * @Date: 2019/11/30 8:31
 * @E-mail： meizxbj@gmail.com
 * @Description: LogUtils
 * @Version: V1.0.0
 */
public class LogUtils {
    /***
     * 获取操作信息
     * @param point
     * @return
     */
    public static String getControllerMethodDescription(JoinPoint point) throws Exception {
        String targetName = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        Object[] args = point.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == args.length) {
                    description = method.getAnnotation(SystemLogAnnotat.class).descrption();
                    break;
                }
            }
        }
        return description;
    }


    /**
     * 获取堆栈信息
     * @param throwable
     * @return
     */
    public static String getStackTrace(Throwable throwable){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try{
            throwable.printStackTrace(pw);
            return sw.toString();
        }finally {
            try {
                if(pw != null){
                    pw.close();
                }
                if(sw != null){
                    sw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
