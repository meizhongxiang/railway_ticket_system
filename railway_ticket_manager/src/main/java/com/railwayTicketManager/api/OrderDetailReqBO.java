package com.railwayTicketManager.api;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: meizx
 * @ClassName: OrderDetailReqBO
 * @Date: 2019/12/8 15:30
 * @E-mail： meizxbj@gmail.com
 * @Description: OrderDetailReqBO
 * @Version: V1.0.0
 */
@Data
public class OrderDetailReqBO implements Serializable {
    @NotNull
    private String userAccountId;
    @NotNull
    private int userId;
    private int page;
}
