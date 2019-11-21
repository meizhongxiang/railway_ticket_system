package com.railwayTicketManager.dao.po;

/**
 * @Author: meizx
 * @ClassName: MemberPO
 * @Date: 2019/11/28 20:44
 * @E-mail： meizxbj@gmail.com
 * @Description: MemberPO
 * @Version: V1.0.0
 */
public class MemberPO {
    private Integer userId;
    private String accountId;
    private Integer grade;
    private Integer integral;

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

    @Override
    public String toString() {
        return "MemberPO{" +
                "userId=" + userId +
                ", accountId='" + accountId + '\'' +
                ", grade=" + grade +
                ", integral=" + integral +
                '}';
    }
}
