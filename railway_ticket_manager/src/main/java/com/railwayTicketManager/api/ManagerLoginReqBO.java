package com.railwayTicketManager.api;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: meizx
 * @ClassName: ManagerLoginReqBO
 * @Date: 2019/12/7 14:52
 * @E-mail： meizxbj@gmail.com
 * @Description: ManagerLoginReqBO
 * @Version: V1.0.0
 */
@Data
public class ManagerLoginReqBO  implements Serializable {
    private String accountId;
    private String userPassword;
}
