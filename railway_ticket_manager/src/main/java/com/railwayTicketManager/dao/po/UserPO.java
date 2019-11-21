package com.railwayTicketManager.dao.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: meizx
 * @ClassName: UserPO
 * @Date: 2019/11/27 20:05
 * @E-mail： meizxbj@gmail.com
 * @Description: UserPO
 * @Version: V1.0.0
 */
public class UserPO {
    //用户标识
    private Integer userId;
    //账户，邮箱
    private String accountId;
    //用户密码
    private String userPassword;
    //验证码
    private String userCode;
    //用户实名
    private String userName;
    //手机号
    private String userPhone;
    //用户邮箱
    private String userMail;
    //绑定银行卡
    private String userBankId;
    //银行预留手机号
    private String userBankPhone;
    //支付密码
    private String payPassword;
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

    private String cardId;

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

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserBankPhone() {
        return userBankPhone;
    }

    public void setUserBankPhone(String userBankPhone) {
        this.userBankPhone = userBankPhone;
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

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserBankId() {
        return userBankId;
    }

    public void setUserBankId(String userBankId) {
        this.userBankId = userBankId;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
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

    @Override
    public String toString() {
        return "UserPO{" +
                "userId=" + userId +
                ", accountId='" + accountId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userMail='" + userMail + '\'' +
                ", userBankId='" + userBankId + '\'' +
                ", userBankPhone='" + userBankPhone + '\'' +
                ", payPassword='" + payPassword + '\'' +
                ", balance='" + balance + '\'' +
                ", createTime=" + createTime +
                ", userStatus=" + userStatus +
                ", endTime=" + endTime +
                '}';
    }
}
