package com.railwayTicketManager.dao;

import com.railwayTicketManager.dao.po.MemberPO;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: meizx
 * @ClassName: MemberDAO
 * @Date: 2019/11/28 20:46
 * @E-mail： meizxbj@gmail.com
 * @Description: MemberDAO
 * @Version: V1.0.0
 */
public interface MemberDAO {
    int infoAdd(MemberPO memberPO);
    int addIntegral(MemberPO memberPO);
    int reduceIntegral(MemberPO memberPO);
    MemberPO selectInfo(@Param("userId") int userId);
    int updateGrade(@Param("userId") int userId);
    int delete(@Param("userId") int userId);
}
