package com.railwayTicketManager.services;

/**
 * @Author: Qidian
 * @ClassName: SelectSequence
 * @Date: 2019-06-27 10:41
 * @E-mail： qidian@jiamsh.com
 * @Description:序列
 * @Version: V1.0.0
 */
public interface SelectSequence {
    /**
     * 获取序列SEQUENCE的方法
     */
    int selectSeq(String seqName);

    int getSequence(String seqName);
}
