package com.railwayTicketManager.services;

import com.railwayTicketManager.api.UserReqBO;
import com.railwayTicketManager.api.UserRspBO;

/**
 * @Author: meizx
 * @ClassName: userSercive
 * @Date: 2019/11/19 19:37
 * @E-mail： meizxbj@gmail.com
 * @Description: userSercive
 * @Version: V1.0.0
 */
public interface UserSercive {
    //用户注册
    UserRspBO register(UserReqBO userReqBO);
    //密码登录
    UserRspBO login(UserReqBO userReqBO);
    //注销账户
    UserRspBO deleteAccount(UserReqBO userReqBO);
    //修改账户
    UserRspBO updateAccount(UserReqBO userReqBO);
    //忘记密码
    UserRspBO forgetPwd(UserReqBO userReqBO);
    //个人详情
    UserRspBO personInfo(UserReqBO userReqBO);
    //快捷登录
    UserRspBO fastLogin(UserReqBO userReqBO);
    //实名
    UserRspBO realName(UserReqBO userReqBO);
}
