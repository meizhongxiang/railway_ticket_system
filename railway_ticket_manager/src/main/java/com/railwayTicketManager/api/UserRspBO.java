package com.railwayTicketManager.api;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.railwayTicketManager.baseBO.RspInfoBO;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: meizx
 * @ClassName: UserRspBO
 * @Date: 2019/11/27 20:05
 * @E-mail： meizxbj@gmail.com
 * @Description: UserRspBO
 * @Version: V1.0.0
 */
public class UserRspBO implements Serializable {
    //用户标识
    private Integer userId;
    //账户，邮箱
    private String accountId;
    //用户密码
    private String userPassword;
    //验证码
    private String code;
    //用户实名
    private String userName;
    //手机号
    private String userPhone;
    //用户邮箱
    /*private String userMail;*/
    //绑定银行卡
    private String userBankId;
    //银行预留手机号
    private String userTel;
    //支付密码
    private String payPassword;
    private String cardId;
    //余额
    private String balance;
    //创建时间
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    //用户状态  0注销  1实名在用  2未实名
    private Integer userStatus;
    //注销时间
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    //等级
    private Integer grade;
    //积分
    private Integer integral;
    //登录状态
    private Integer loginst;
    private String rspCode;
    private String rspDesc;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserBankId() {
        return userBankId;
    }

    public void setUserBankId(String userBankId) {
        this.userBankId = userBankId;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getLoginst() {
        return loginst;
    }

    public void setLoginst(Integer loginst) {
        this.loginst = loginst;
    }

    public String getRspCode() {
        return rspCode;
    }

    public void setRspCode(String rspCode) {
        this.rspCode = rspCode;
    }

    public String getRspDesc() {
        return rspDesc;
    }

    public void setRspDesc(String rspDesc) {
        this.rspDesc = rspDesc;
    }

    @Override
    public String toString() {
        return "UserRspBO{" +
                "userId=" + userId +
                ", accountId='" + accountId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", code='" + code + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userBankId='" + userBankId + '\'' +
                ", userTel='" + userTel + '\'' +
                ", payPassword='" + payPassword + '\'' +
                ", cardId='" + cardId + '\'' +
                ", balance='" + balance + '\'' +
                ", createTime=" + createTime +
                ", userStatus=" + userStatus +
                ", endTime=" + endTime +
                ", grade=" + grade +
                ", integral=" + integral +
                ", loginst=" + loginst +
                ", rspCode='" + rspCode + '\'' +
                ", rspDesc='" + rspDesc + '\'' +
                '}';
    }
}
