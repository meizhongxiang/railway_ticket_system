package com.railwayTicketManager.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.railwayTicketManager.api.UserReqBO;
import com.railwayTicketManager.api.UserRspBO;
import com.railwayTicketManager.baseBO.Constants;
import com.railwayTicketManager.config.RedisClient;
import com.railwayTicketManager.config.UserDefinedException;
import com.railwayTicketManager.config.log.SystemLogAnnotat;
import com.railwayTicketManager.dao.IdCardDAO;
import com.railwayTicketManager.dao.MemberDAO;
import com.railwayTicketManager.dao.UserDAO;
import com.railwayTicketManager.dao.po.IdCardPO;
import com.railwayTicketManager.dao.po.MemberPO;
import com.railwayTicketManager.dao.po.UserPO;
import com.railwayTicketManager.services.SelectSequence;
import com.railwayTicketManager.services.UserSercive;
import com.railwayTicketManager.utils.IDCardUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @Author: meizx
 * @ClassName: UserSerciveImpl
 * @Date: 2019/11/19 19:41
 * @E-mail： meizxbj@gmail.com
 * @Description: UserSerciveImpl
 * @Version: V1.0.0
 */
@Service
@Slf4j
public class UserSerciveImpl implements UserSercive {
    @Autowired
    private UserDAO userDao;
    @Autowired
    private SelectSequence selectSequence;
    @Autowired
    private RedisClient redisClient;
    @Autowired
    private IdCardDAO idCardDAO;
    @Autowired
    private MemberDAO memberDAO;

    @Override
    @SystemLogAnnotat(descrption = "用户注册")
    @Transactional
    public UserRspBO register(UserReqBO userReqBO) {
        UserRspBO userRspBO = new UserRspBO();

        //检验必需的参数，尽量减少空异常，请求参数不足时，直接返回
        if("".equals(userReqBO.getAccountId()) || userReqBO.getAccountId() == null){
            userRspBO.setRspDesc("账户参数为空");
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }
        if("".equals(userReqBO.getCode()) || userReqBO.getCode() == null){
            userRspBO.setRspDesc("验证码为空");
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }
        String yzm = null;
        String strObj = null;

        //可能出现异常的地方用try catch
        try {
            yzm = redisClient.getCode(userReqBO.getAccountId());
            strObj = redisClient.get(userReqBO.getAccountId());
        } catch (Exception e) {
            userRspBO.setRspDesc("验证码错误");
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }
        if(!userReqBO.getCode().equals(yzm)){
            userRspBO.setRspDesc("验证码检查错误");
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }

        if(!(" ".equals(strObj) || strObj == null)){
            userRspBO.setRspDesc("账号已注册");
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }else if(userDao.selectLoginCount(userReqBO.getAccountId()) > 0){
            userRspBO.setRspDesc("账号已注册");
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }

        //参数校验完成后处理请求做的事情
        UserPO userPO = new UserPO();
        userPO.setUserId(selectSequence.getSequence(Constants.USER_ID));
        userPO.setUserMail(userReqBO.getAccountId());
        userPO.setAccountId(userReqBO.getAccountId());
        userPO.setUserPassword(userReqBO.getUserPassword());
        userPO.setCreateTime(LocalDateTime.now());
        userPO.setUserStatus(2);
        log.debug("落库数据========"+userPO.toString());
        if(userDao.userRegister(userPO) > 0 && userDao.insertLogin(userPO) > 0){
            userRspBO.setUserId(userPO.getUserId());
            userRspBO.setAccountId(userPO.getAccountId());
            userRspBO.setUserStatus(2);
            userRspBO.setRspDesc(Constants.RSP_DESC_SUCCESS);
            userRspBO.setRspCode(Constants.RSP_CODE_SUCCESS);
            try {
                redisClient.set(userPO.getAccountId(), JSONObject.toJSONString(userPO));
            } catch (UserDefinedException e) {
                e.printStackTrace();
            }
        }
        log.debug("返回数据======="+userRspBO.toString());
        return userRspBO;
    }

    @SystemLogAnnotat(descrption = "用户密码登录")
    @Override
    public UserRspBO login(UserReqBO userReqBO) {
        UserRspBO userRspBO = new UserRspBO();
        if("".equals(userReqBO.getAccountId()) || userReqBO.getAccountId() == null){
            userRspBO.setRspDesc(Constants.RSP_DESC_FAILUR);
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }
        if("".equals(userReqBO.getUserPassword()) || userReqBO.getUserPassword() == null){
            userRspBO.setRspDesc(Constants.RSP_DESC_FAILUR);
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }
        String objStr = null;
        try {
            objStr = redisClient.get(userReqBO.getAccountId());
            JSONObject jso = JSONObject.parseObject(objStr);
            UserPO userPO = JSONObject.toJavaObject(jso,UserPO.class);
            if(!(("".equals(userPO.getAccountId()) && "".equals(userPO.getUserPassword()) /*&& "".equals(userPO.getUserCode())*/) || userPO == null || (userPO.getUserId() == null /*&& userPO.getUserCode() == null*/))){
                /*if("1".equals(userPO.getUserCode())){
                    userRspBO.setRspDesc("账户已在");
                    userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
                    return userRspBO;
                }*/

                if(userReqBO.getUserPassword().equals(userPO.getUserPassword())){
                    userRspBO.setAccountId(userPO.getAccountId());
                    userRspBO.setUserId(userPO.getUserId());
                    userRspBO.setUserStatus(userPO.getUserStatus());
                    userRspBO.setRspDesc(Constants.RSP_DESC_SUCCESS);
                    userRspBO.setRspCode(Constants.RSP_CODE_SUCCESS);
                    userRspBO.setLoginst(1);
                    return userRspBO;
                }else{
                    userRspBO.setAccountId(userPO.getAccountId());
                    userRspBO.setRspDesc("密码不正确");
                    userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
                    return userRspBO;
                }
            }else{
                objStr = null;
            }
        } catch (Exception e) {
            objStr = null;
        }

        if("".equals(objStr) || objStr ==null ){
            UserPO userPO = new UserPO();
            userPO.setAccountId(userReqBO.getAccountId());
            userPO.setUserPassword(userReqBO.getUserPassword());
            UserPO rtUserPO = userDao.checkAccountByPwd(userPO);
            if(rtUserPO == null || "".equals(rtUserPO.getUserId())){
                userRspBO.setRspDesc("账号未注册或密码不正确");
                userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
                return userRspBO;
            }
            userRspBO.setAccountId(userPO.getAccountId());
            userRspBO.setUserId(rtUserPO.getUserId());
            userRspBO.setUserStatus(rtUserPO.getUserStatus());
            userRspBO.setRspDesc(Constants.RSP_DESC_SUCCESS);
            userRspBO.setRspCode(Constants.RSP_CODE_SUCCESS);
            userRspBO.setLoginst(1);
            try {
                redisClient.set(userPO.getAccountId(), JSONObject.toJSONString(rtUserPO));
            } catch (Exception e) {
            }
        }
        return userRspBO;
    }

    @Override
    @SystemLogAnnotat(descrption = "用户注销账户")
    @Transactional
    public UserRspBO deleteAccount(UserReqBO userReqBO) {
        UserRspBO userRspBO = new UserRspBO();
        if("".equals(userReqBO.getAccountId()) || userReqBO.getAccountId() == null){
            userRspBO.setRspDesc(Constants.RSP_DESC_FAILUR);
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }
        if("".equals(userReqBO.getUserId()) || userReqBO.getUserId() == null){
            userRspBO.setRspDesc(Constants.RSP_DESC_FAILUR);
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }
        if("".equals(userReqBO.getCode()) || userReqBO.getCode() == null){
            userRspBO.setRspDesc(Constants.RSP_DESC_FAILUR);
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }
        if("".equals(userReqBO.getUserPassword()) || userReqBO.getUserPassword() == null){
            userRspBO.setRspDesc(Constants.RSP_DESC_FAILUR);
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }
        String yzm = null;
        UserPO userPO = null;
        try {
            yzm = redisClient.getCode(userReqBO.getAccountId());
            String objStr = redisClient.get(userReqBO.getAccountId());
            JSONObject jso = JSONObject.parseObject(objStr);
            userPO = JSONObject.toJavaObject(jso,UserPO.class);
        } catch (UserDefinedException e) {

        }
        if(userReqBO.getCode().equals(yzm)){
            if(userReqBO.getUserPassword().equals(userPO.getUserPassword())){
                userPO.setUserStatus(0);
                if(userDao.deleteByUserId(userPO) > 0 && userDao.updateUserStatus(userPO) > 0){
                    try {
                        redisClient.delete(userReqBO.getAccountId());
                    } catch (UserDefinedException e) {

                    }
                    userRspBO.setRspDesc(Constants.RSP_DESC_SUCCESS);
                    userRspBO.setRspCode(Constants.RSP_CODE_SUCCESS);
                    userRspBO.setLoginst(0);
                }
            }
        }else{
            userRspBO.setRspDesc("验证码不正确");
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }
        return userRspBO;
    }

    @Override
    @SystemLogAnnotat(descrption = "用户实名")
    @Transactional
    public UserRspBO realName(UserReqBO userReqBO) {
        UserRspBO userRspBO = new UserRspBO();
        if("".equals(userReqBO.getAccountId()) || userReqBO.getAccountId() == null){
            userRspBO.setRspDesc("参数为空");
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }
        if("".equals(userReqBO.getUserId()) || userReqBO.getUserId() == null){
            userRspBO.setRspDesc("参数为空");
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }
        if(userReqBO.getCardId().length() == 18){
            if(!IDCardUtil.checkIDCard(userReqBO.getCardId())){
                userRspBO.setRspDesc("身份证号码不正确");
                userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
                return userRspBO;
            }
        }
        String yzm = null;
        try {
            yzm = redisClient.getCode(userReqBO.getAccountId());
        } catch (UserDefinedException e) {

        }
        if(!yzm.equals(userReqBO.getCode())){
            userRspBO.setRspDesc("验证码不正确");
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }
        UserPO userPO = new UserPO();
        IdCardPO idCardPO = new IdCardPO();
        MemberPO memberPO = new MemberPO();
        userPO.setUserName(userReqBO.getUserName());
        userPO.setUserPhone(userReqBO.getUserPhone());
        userPO.setUserBankPhone(userReqBO.getUserTel());
        userPO.setUserStatus(1);
        userPO.setPayPassword(userReqBO.getPayPassword());
        userPO.setUserBankId(userReqBO.getUserBankId());
        userPO.setUserId(userReqBO.getUserId());
        userPO.setAccountId(userReqBO.getAccountId());
        idCardPO.setCardId(userReqBO.getCardId());
        idCardPO.setUserBankId(userReqBO.getUserBankId());
        idCardPO.setUserName(userReqBO.getUserName());
        idCardPO.setUserId(userReqBO.getUserId());
        memberPO.setAccountId(userReqBO.getAccountId());
        memberPO.setUserId(userReqBO.getUserId());
        memberPO.setIntegral(5000);
        if(idCardDAO.userIdCardAdd(idCardPO) > 0
                && userDao.realNameAccount(userPO) > 0
                && memberDAO.infoAdd(memberPO) > 0){
            userDao.updateStatus(userPO);
            try {
                String str = redisClient.get(userPO.getAccountId());
                JSONObject jso = JSONObject.parseObject(str);
                UserPO user = JSONObject.toJavaObject(jso,UserPO.class);
                user.setUserStatus(1);
                redisClient.set(user.getAccountId(), JSONObject.toJSONString(user));
            } catch (UserDefinedException e) {
                e.printStackTrace();
            }
            userRspBO.setUserStatus(1);
            userRspBO.setRspDesc(Constants.RSP_DESC_SUCCESS);
            userRspBO.setRspCode(Constants.RSP_CODE_SUCCESS);
        }
        return userRspBO;
    }

    @Override
    @SystemLogAnnotat(descrption = "用户修改信息")
    @Transactional
    public UserRspBO updateAccount(UserReqBO userReqBO) {
        UserRspBO userRspBO = new UserRspBO();
        if("".equals(userReqBO.getAccountId()) || userReqBO.getAccountId() == null){
            userRspBO.setRspDesc("参数为空");
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }
        if("".equals(userReqBO.getUserId()) || userReqBO.getUserId() == null){
            userRspBO.setRspDesc("参数为空");
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }
        String yzm = null;
        try {
            yzm = redisClient.getCode(userReqBO.getAccountId());
        } catch (UserDefinedException e) {

        }
        if(!yzm.equals(userReqBO.getCode())){
            userRspBO.setRspDesc("验证码不正确");
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }
        UserPO userPO = new UserPO();
        IdCardPO idCardPO = new IdCardPO();
        userPO.setUserPhone(userReqBO.getUserPhone());
        userPO.setUserBankPhone(userReqBO.getUserTel());
        userPO.setPayPassword(userReqBO.getPayPassword());
        userPO.setUserBankId(userReqBO.getUserBankId());
        userPO.setUserId(userReqBO.getUserId());
        userPO.setAccountId(userReqBO.getAccountId());
        idCardPO.setCardId(userReqBO.getCardId());
        idCardPO.setUserBankId(userReqBO.getUserBankId());
        idCardPO.setUserId(userReqBO.getUserId());
        if(idCardDAO.updateCardId(idCardPO) > 0
                && userDao.updateByIdAndAccount(userPO) > 0){
            try {
                String str = redisClient.get(userPO.getUserId().toString());
                JSONObject jso = JSONObject.parseObject(str);
                UserPO user = JSONObject.toJavaObject(jso,UserPO.class);
                user.setUserPhone(userReqBO.getUserPhone());
                user.setUserBankPhone(userReqBO.getUserTel());
                user.setPayPassword(userReqBO.getPayPassword());
                user.setUserBankId(userReqBO.getUserBankId());
                redisClient.set(user.getUserId().toString(),JSONObject.toJSONString(user));
            } catch (UserDefinedException e) {

            }
            userRspBO.setRspDesc(Constants.RSP_DESC_SUCCESS);
            userRspBO.setRspCode(Constants.RSP_CODE_SUCCESS);
        }
        return userRspBO;
    }

    @Override
    @SystemLogAnnotat(descrption = "用户忘记密码")
    @Transactional
    public UserRspBO forgetPwd(UserReqBO userReqBO) {
        UserRspBO userRspBO = new UserRspBO();
        if("".equals(userReqBO.getAccountId()) || userReqBO.getAccountId() == null){
            userRspBO.setRspDesc(Constants.RSP_DESC_FAILUR);
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }
        if("".equals(userReqBO.getCode()) || userReqBO.getCode() == null){
            userRspBO.setRspDesc(Constants.RSP_DESC_FAILUR);
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }
        String yzm = null;
        UserPO userPO = null;
        try {
            yzm = redisClient.getCode(userReqBO.getAccountId());
            String objStr = redisClient.get(userReqBO.getAccountId());
            JSONObject jso = JSONObject.parseObject(objStr);
            userPO = JSONObject.toJavaObject(jso,UserPO.class);
        } catch (UserDefinedException e) {

        }
        if(!userReqBO.getCode().equals(yzm)){
            userRspBO.setRspDesc("验证码不正确");
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }
        if(userPO == null || userPO.getUserId() == null || "".equals(userPO.getAccountId())){
            userPO = userDao.checkAccount(userReqBO.getAccountId());
            if(userPO == null || userPO.getUserId() == null || "".equals(userPO.getAccountId())){
                userRspBO.setRspDesc("账号未注册");
                userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
                return userRspBO;
            }
        }
        if(userPO.getUserPassword().equals(userReqBO.getUserPassword())){
            userRspBO.setRspDesc("新密码不能与最近使用的密码相同");
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }
        userPO.setUserPassword(userReqBO.getUserPassword());
        if(userDao.forgetPwdByAccount(userPO) > 0){
            try {
                redisClient.set(userPO.getAccountId(),JSONObject.toJSONString(userPO));
            } catch (UserDefinedException e) {

            }
            userRspBO.setRspDesc(Constants.RSP_DESC_SUCCESS);
            userRspBO.setRspCode(Constants.RSP_CODE_SUCCESS);
        }
        return userRspBO;
    }

    @Override
    @SystemLogAnnotat(descrption = "用户个人详情")
    public UserRspBO personInfo(UserReqBO userReqBO) {
        UserRspBO userRspBO = new UserRspBO();
        if("".equals(userReqBO.getAccountId()) || userReqBO.getAccountId() == null){
            userRspBO.setRspDesc(Constants.RSP_DESC_FAILUR);
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }
        if("".equals(userReqBO.getUserId()) || userReqBO.getUserId() == null){
            userRspBO.setRspDesc(Constants.RSP_DESC_FAILUR);
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }
        UserPO userPO = null;
        try {
            String objStr = redisClient.get(userReqBO.getUserId().toString());
            JSONObject jso = JSONObject.parseObject(objStr);
            userPO = JSONObject.toJavaObject(jso,UserPO.class);
        } catch (UserDefinedException e) {

        }
        if(userPO == null){
            UserPO user = new UserPO();
            user.setAccountId(userReqBO.getAccountId());
            user.setUserId(userReqBO.getUserId());
            userPO = userDao.selectPersonInfo(user);
            try {
                redisClient.set(userPO.getUserId().toString(), JSONObject.toJSONString(userPO));
            } catch (UserDefinedException e) {

            }
        }
        IdCardPO idCardPO = idCardDAO.selectInfo(userPO.getUserId());
        MemberPO memberPO = memberDAO.selectInfo(userPO.getUserId());
        userRspBO.setUserName(userPO.getUserName());
        userRspBO.setCardId(idCardPO.getCardId());
        userRspBO.setUserPhone(userPO.getUserPhone());
        userRspBO.setAccountId(userPO.getAccountId());
        userRspBO.setUserBankId(userPO.getUserBankId());
        //userRspBO.setCreateTime(userPO.getCreateTime());
        userRspBO.setUserTel(userPO.getUserBankPhone());
        userRspBO.setPayPassword(userPO.getPayPassword());
        userRspBO.setGrade(memberPO.getGrade());
        userRspBO.setIntegral(memberPO.getIntegral());
        userRspBO.setRspDesc(Constants.RSP_DESC_SUCCESS);
        userRspBO.setRspCode(Constants.RSP_CODE_SUCCESS);
        return userRspBO;
    }

    @Override
    @SystemLogAnnotat(descrption = "用户快捷登录")
    public UserRspBO fastLogin(UserReqBO userReqBO) {
        UserRspBO userRspBO = new UserRspBO();
        if("".equals(userReqBO.getAccountId()) || userReqBO.getAccountId() == null){
            userRspBO.setRspDesc(Constants.RSP_DESC_FAILUR);
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }
        if("".equals(userReqBO.getCode()) || userReqBO.getCode() == null){
            userRspBO.setRspDesc(Constants.RSP_DESC_FAILUR);
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }

        String yzm = null;
        UserPO userPO = null;
        try {
            yzm = redisClient.getCode(userReqBO.getAccountId());
            String objStr = redisClient.get(userReqBO.getAccountId());
            JSONObject jso = JSONObject.parseObject(objStr);
            userPO = JSONObject.toJavaObject(jso,UserPO.class);
        } catch (UserDefinedException e) {

        }
        if(!userReqBO.getCode().equals(yzm)){
            userRspBO.setRspDesc(Constants.YZM_DESC_FAILUR);
            userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
            return userRspBO;
        }else{
            if(userPO == null || userPO.getUserId() == null || "".equals(userPO.getAccountId())){
                userPO = userDao.checkAccount(userReqBO.getAccountId());
                if(userPO == null || userPO.getUserId() == null || "".equals(userPO.getAccountId())){
                    userRspBO.setRspDesc("账户不存在");
                    userRspBO.setRspCode(Constants.RSP_CODE_FAILUR);
                    return userRspBO;
                }
                userRspBO.setUserStatus(userPO.getUserStatus());
                userRspBO.setAccountId(userPO.getAccountId());
                userRspBO.setUserId(userPO.getUserId());
                userRspBO.setRspDesc(Constants.RSP_DESC_SUCCESS);
                userRspBO.setRspCode(Constants.RSP_CODE_SUCCESS);
                userRspBO.setLoginst(1);
                try {
                    redisClient.set(userPO.getAccountId(), JSONObject.toJSONString(userPO));
                } catch (UserDefinedException e) {

                }
            }else{
                userRspBO.setUserStatus(userPO.getUserStatus());
                userRspBO.setAccountId(userPO.getAccountId());
                userRspBO.setUserId(userPO.getUserId());
                userRspBO.setRspDesc(Constants.RSP_DESC_SUCCESS);
                userRspBO.setRspCode(Constants.RSP_CODE_SUCCESS);
                userRspBO.setLoginst(1);
            }
        }
        return userRspBO;
    }
}
