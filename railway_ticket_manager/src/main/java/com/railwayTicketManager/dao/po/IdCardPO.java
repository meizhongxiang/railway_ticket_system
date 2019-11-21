package com.railwayTicketManager.dao.po;

/**
 * @Author: meizx
 * @ClassName: IdCardPO
 * @Date: 2019/11/28 19:24
 * @E-mail： meizxbj@gmail.com
 * @Description: IdCardPO
 * @Version: V1.0.0
 */
public class IdCardPO {
    private Integer userId;
    private String cardId;
    private String userName;
    private String userBankId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserBankId() {
        return userBankId;
    }

    public void setUserBankId(String userBankId) {
        this.userBankId = userBankId;
    }

    @Override
    public String toString() {
        return "IdCardPO{" +
                "userId=" + userId +
                ", cardId='" + cardId + '\'' +
                ", userName='" + userName + '\'' +
                ", userBankId='" + userBankId + '\'' +
                '}';
    }
}
