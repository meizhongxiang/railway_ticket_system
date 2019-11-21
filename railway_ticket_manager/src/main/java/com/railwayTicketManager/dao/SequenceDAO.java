package com.railwayTicketManager.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @Author: Qidian
 * @ClassName: SequenceDAO
 * @Date: 2019-06-27 10:41
 * @E-mail： qidian@jiamsh.com
 * @Description:sequence获取值
 * @Version: V1.0.0
 */
public interface SequenceDAO {
    //sequence现值查询
    int selectSequence(@Param("seqName") String seqName);
    //sequence值更新
    int updateSequence(@Param("seqName") String seqName);
}
