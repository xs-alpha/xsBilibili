package com.xiaosheng.video.services.service;

import com.xiaosheng.video.dao.mapper.UserInfoPOMapper;
import com.xiaosheng.video.dao.mapper.UserPOMapper;
import com.xiaosheng.video.dao.po.UserInfoPO;
import com.xiaosheng.video.dao.po.UserPO;
import com.xiaosheng.video.facade.constant.UserConstant;
import com.xiaosheng.video.facade.dto.UserDto;
import com.xiaosheng.video.facade.enums.BizResultCodeEnum;
import com.xiaosheng.video.services.util.MD5Util;
import com.xiaosheng.video.services.util.RSAUtil;
import com.xiaosheng.video.support.result.Result;
import com.xiaosheng.video.support.utils.BeanConvertorUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Results;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Transactional
public class UserService {
    @Resource
    private UserPOMapper userPOMapper;

    @Resource
    private UserInfoPOMapper userInfoPOMapper;

    public Result addUser(UserDto userDto) {
        String phone = userDto.getPhone();
        if (StringUtils.isBlank(phone)) {
            // 手机号不能为空
            return Result.buildResult(BizResultCodeEnum.CODE_ERR_PHONE);
        }
        UserPO user = userPOMapper.getUserByPhone(phone);
        if (user != null) {
            // 该手机号已注册
            return Result.buildResult(BizResultCodeEnum.PHONE_ALREADY_REGISTERED);
        }
        String salt = String.valueOf(new Date().getTime());
        String password = userDto.getPassword();

        String rawPassword = "";
        try {
            rawPassword = RSAUtil.decrypt(password);
        } catch (Exception e) {
            e.printStackTrace();
            // 密码解密失败
            return Result.buildResult(BizResultCodeEnum.PASSWORD_PARSE_EXCEPTION);
        }
        String md5Password = MD5Util.sign(rawPassword, salt, "UTF-8");

        /**
         * 插入注册登录表
         */
        UserPO userPO = new UserPO();
        userPO.setSalt(salt);
        userPO.setPassword(md5Password);
        userPO.setPhone(phone);
        userPO.setEmail(userDto.getEmail());
        userPOMapper.insert(userPO);

        /**
         * 根据手机号在注册登录表查询用户信息
         * 插入用户信息表
         */
        UserPO queryUser = userPOMapper.getUserByPhone(phone);
        UserInfoPO userInfoPO = new UserInfoPO();
        userInfoPO.setAvatar(UserConstant.AVATAR);
        userInfoPO.setBirth(UserConstant.DEFAULT_BIRTH);
        userInfoPO.setGender(UserConstant.GENDER_UNKNOWN);
        userInfoPO.setUserid(queryUser.getId());
        userInfoPO.setNick(UserConstant.NICK_NAME);
        userInfoPOMapper.insert(userInfoPO);

        // todo: controller找不到beancontertutils
        BeanConvertorUtils.copy(userPO, userInfoPO);
        return Result.buildSuccessResult();
    }


}
