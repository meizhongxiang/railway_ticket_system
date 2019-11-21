package com.railwayTicketManager.dao;

import com.railwayTicketManager.dao.po.IdCardPO;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: meizx
 * @ClassName: IdCardDAO
 * @Date: 2019/11/28 19:36
 * @E-mail： meizxbj@gmail.com
 * @Description: IdCardDAO
 * @Version: V1.0.0
 */
public interface IdCardDAO {
    int userIdCardAdd(IdCardPO idCardPO);
    IdCardPO selectInfo(@Param("userId") int userId);
    int updateCardId(IdCardPO idCardPO);
}
