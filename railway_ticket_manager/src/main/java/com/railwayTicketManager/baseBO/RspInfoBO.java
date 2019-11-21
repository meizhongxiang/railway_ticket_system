package com.railwayTicketManager.baseBO;

import java.io.Serializable;

/**
 * @Author: Qidian
 * @ClassName: RspInfoBO
 * @Date: 2019-06-27 10:41
 * @E-mail： qidian@jiamsh.com
 * @Description:返回数据
 * @Version: V1.0.0
 */
public class RspInfoBO  implements Serializable {

    private String rspCode;
    private String rspDesc;

    private Integer uid = null;
    private String mail = null;

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

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    @Override
    public String toString() {
        return "RspInfoBO{" +
                "rspCode='" + rspCode + '\'' +
                ", rspDesc='" + rspDesc + '\'' +
                ", uid=" + uid +
                ", mail='" + mail + '\'' +
                '}';
    }
}
