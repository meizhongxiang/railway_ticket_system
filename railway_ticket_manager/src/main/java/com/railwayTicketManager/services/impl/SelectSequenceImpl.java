package com.railwayTicketManager.services.impl;


import com.railwayTicketManager.config.RedisClient;
import com.railwayTicketManager.dao.SequenceDAO;
import com.railwayTicketManager.services.SelectSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Qidian
 * @ClassName: SelectSequenceImpl
 * @Date: 2019-06-27 10:41
 * @E-mail： qidian@jiamsh.com
 * @Description:获取序列服务
 * @Version: V1.0.0
 */
@Service
public class SelectSequenceImpl implements SelectSequence {
    @Autowired
    private SequenceDAO sequenceDAO;
    @Autowired
    private RedisClient redisClient;
    @Override
    public int selectSeq(String seqName) {
        int currentValue = 0;
        try {
            currentValue = sequenceDAO.selectSequence(seqName);
            sequenceDAO.updateSequence(seqName);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("序列获取异常");
        }
        return currentValue;
    }
    public int getSequence(String seqName) {
        int currentValue = 0;
        try {
            currentValue = redisClient.getSequence(seqName);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("序列获取异常");
        }
        return currentValue;
    }
}
