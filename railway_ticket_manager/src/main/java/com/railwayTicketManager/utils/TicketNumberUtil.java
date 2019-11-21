package com.railwayTicketManager.utils;

import com.railwayTicketManager.dao.po.TrainDetailPO;

/**
 * @Author: meizx
 * @ClassName: TicketNumberUtil
 * @Date: 2019/12/8 13:10
 * @E-mail： meizxbj@gmail.com
 * @Description: TicketNumberUtil
 * @Version: V1.0.0
 */
public class TicketNumberUtil {
    public String rtNumber(TrainDetailPO trainDetailPO, int clas){
        int sale=0;
        int trainId = 0;
        int line = 0;
        int position = 0;
        char c=' ';
        if(clas == 1){
            sale =  trainDetailPO.getSumOne() - trainDetailPO.getClassOne();//获得余量 500-240=260
            trainId = sale/100+1;//获得车厢号 260/100+1= 3
            line = sale%100/5+1;//获得座排号
            position = sale%100%5;//获得座号
            if(position == 0){
                c='E';
            }else if(position == 1){
                line++;
                c='A';
            }else if(position == 2){
                line++;
                c='B';
            }else if(position == 3){
                line++;
                c='C';
            }else if(position == 4){
                line++;
                c='E';
            }else{}
        }else{
            sale =  trainDetailPO.getSumOne() - trainDetailPO.getClassOne();//获得余量 500-240=260
            trainId = sale/100+6;//获得车厢号 260/100+6= 3
            line = sale%100/5;//获得座排号
            position = sale%100%5;//获得座号
            if(position == 0){
                c='E';
            }else if(position == 1){
                line++;
                c='A';
            }else if(position == 2){
                line++;
                c='B';
            }else if(position == 3){
                line++;
                c='C';
            }else if(position == 4){
                line++;
                c='E';
            }else{}
        }
        return trainId+"车"+line+""+c;
    }
}
