package com.railwayTicketManager.api;

import com.alibaba.fastjson.annotation.JSONField;
import com.railwayTicketManager.baseBO.ReqInfoBO;

import java.util.Date;

/**
 * @Author: meizx
 * @ClassName: UserReqBO
 * @Date: 2019/11/27 20:05
 * @E-mail： meizxbj@gmail.com
 * @Description: UserReqBO
 * @Version: V1.0.0
 */
public class UserReqBO extends ReqInfoBO {
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
    private Date createTime;
    //用户状态  0注销  1实名在用  2未实名
    private Integer userStatus;
    //注销时间
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "UserReqBO{" +
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
                '}';
    }
}
