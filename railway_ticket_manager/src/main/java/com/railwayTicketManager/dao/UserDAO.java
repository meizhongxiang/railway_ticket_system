package com.railwayTicketManager.dao;

import com.railwayTicketManager.dao.po.UserPO;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: meizx
 * @ClassName: userDao
 * @Date: 2019/11/27 20:00
 * @E-mail： meizxbj@gmail.com
 * @Description: userDao
 * @Version: V1.0.0
 */
public interface UserDAO {
    //注册用户表
    int userRegister(UserPO userPO);
    //注册登录表
    int insertLogin(UserPO userPO);
    //验证登录
    UserPO checkAccount(@Param("accountId") String accountId);
    UserPO checkAccountByPwd(UserPO userPO);
    //注销账户修改登录表
    int deleteByUserId(UserPO userPO);
    //修改账户
    int realNameAccount(UserPO userPO);
    //忘记密码
    int forgetPwdByAccount(UserPO userPO);
    //个人详情
    UserPO selectPersonInfo(UserPO userPO);
    //注销账户修改用户主表
    int updateUserStatus(UserPO userPO);
    //查询login表
    int selectLoginCount(@Param("accountId") String accountId);
    //注销账户修改用户主表
    void updateStatus(UserPO userPO);

    int updateByIdAndAccount(UserPO userPO);
}
