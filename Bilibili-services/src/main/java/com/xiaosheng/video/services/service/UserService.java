package com.xiaosheng.video.services.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaosheng.video.dao.mapper.UserInfoPOMapper;
import com.xiaosheng.video.dao.mapper.UserPOMapper;
import com.xiaosheng.video.dao.po.UserInfoPO;
import com.xiaosheng.video.dao.po.UserPO;
import com.xiaosheng.video.facade.bo.UserInfoBO;
import com.xiaosheng.video.facade.constant.UserConstant;
import com.xiaosheng.video.facade.dto.FollowingGroupDTO;
import com.xiaosheng.video.facade.dto.UserDTO;
import com.xiaosheng.video.facade.dto.UserInfoDTO;
import com.xiaosheng.video.facade.dto.UserInfoPageDTO;
import com.xiaosheng.video.facade.enums.BizResultCodeEnum;
import com.xiaosheng.video.support.result.PageResult;
import com.xiaosheng.video.support.result.Result;
import com.xiaosheng.video.support.utils.BeanConvertorUtils;
import com.xiaosheng.video.support.utils.MD5Util;
import com.xiaosheng.video.support.utils.RSAUtil;
import com.xiaosheng.video.support.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@Slf4j
public class UserService {
    @Resource
    private UserPOMapper userPOMapper;

    @Resource
    private UserInfoPOMapper userInfoPOMapper;

    @Resource
    private UserFollowingService userFollowingService;

    public UserPO getUserById(Integer followingId) {
        return userPOMapper.getUserById(followingId.longValue());
    }

    /**
     * 添加用户
     *
     * @param userDto
     * @return
     */
    public Result addUser(UserDTO userDto) {
        String phone = userDto.getPhone();
        if (StringUtils.isBlank(phone)) {
            // 手机号不能为空
            return Result.buildResult(BizResultCodeEnum.CODE_ERR_PHONE);
        }
        UserPO user = userPOMapper.getUserByPhone(phone);
        if (user != null) {
            // 该手机号已注册
            return Result.buildResult(BizResultCodeEnum.CODE_PHONE_ALREADY_REGISTERED);
        }
        String salt = String.valueOf(new Date().getTime());
        String password = userDto.getPassword();

        String rawPassword = "";
        try {
            rawPassword = RSAUtil.decrypt(password);
        } catch (Exception e) {
            e.printStackTrace();
            // 密码解密失败
            return Result.buildResult(BizResultCodeEnum.CODE_PASSWORD_PARSE_EXCEPTION);
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
        userPOMapper.insertSelective(userPO);

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
        userInfoPOMapper.insertSelective(userInfoPO);

        // todo: controller找不到beancontertutils
        BeanConvertorUtils.copy(userPO, userInfoPO);
        return Result.buildSuccessResult();
    }

    /**
     * 登录
     *
     * @param userDto
     * @return
     */
    public Result login(UserDTO userDto) {
        String phone = userDto.getPhone();
        if (phone == null || StringUtils.isEmpty(phone)) {
            // 手机号不能为空
            return Result.buildResult(BizResultCodeEnum.CODE_ERR_PHONE);
        }
        UserPO user = userPOMapper.getUserByPhone(phone);
        if (user == null) {
            // 当前用户不存在
            return Result.buildResult(BizResultCodeEnum.CODE_USER_NOT_EXIST);
        }
        // 判断密码是否匹配
        // 用户传进来的密码
        String password = userDto.getPassword();
        String rawPassword = "";
        try {
            rawPassword = RSAUtil.decrypt(password);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("parse password err:{}", e.getMessage());

        }
        String salt = user.getSalt();
        // 获取加密以后的密码
        String md5Password = MD5Util.sign(rawPassword, salt, "UTF-8");
        if (!md5Password.equals(user.getPassword())) {
            // 用户名密码不匹配
            return Result.buildResult(BizResultCodeEnum.CODE_INCORRECT_PASSWORD);
        }
        String token = "";
        try {
            token = TokenUtil.generateToken(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("生成token失败");
            return Result.buildResult(BizResultCodeEnum.CODE_TOKEN_GENERATE_FAILED);
        }
        return Result.buildSuccessResult(token);
    }

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    public Result<UserInfoBO> getUserInfo(Long userId) {
        UserPO user = userPOMapper.getUserById(userId);
        UserInfoPO userInfo = userInfoPOMapper.getUserInfoById(userId);
        UserInfoBO userInfoBO = new UserInfoBO();
        BeanConvertorUtils.copy(user, userInfoBO);
        BeanConvertorUtils.copy(userInfo, userInfoBO);
        return Result.buildSuccessResult(userInfoBO);
    }

    /**
     * 更新用户信息
     **/
    public Result updateUserInfo(UserInfoDTO userInfoDTO) {
        UserInfoPO userInfoPO = new UserInfoPO();
        BeanConvertorUtils.copy(userInfoDTO, userInfoPO);
        userInfoPOMapper.updateByPrimaryKeySelective(userInfoPO);
        return Result.buildSuccessResult();
    }

    public List<UserInfoPO> getUserInfoByUserIds(Set<Long> userIdList) {
        return userInfoPOMapper.getUserInfoByUserIds(userIdList);
    }

    /**
     * 获取用户信息 列表
     *
     * @param userInfoPageDTO
     * @return
     */
    public PageResult<UserInfoBO> pageListUserInfos(UserInfoPageDTO userInfoPageDTO) {
        Page page = new Page();
        page.setCurrent(userInfoPageDTO.getPageNo());
        page.setSize(userInfoPageDTO.getPageSize());

        IPage<UserInfoPO> pageListUserInfos = userInfoPOMapper.getPageListUserInfos(page, userInfoPageDTO.getNick());
        List<UserInfoPO> records = pageListUserInfos.getRecords();
        List<UserInfoBO> userInfoBOS = BeanConvertorUtils.copyList(records, UserInfoBO.class);


        return new PageResult<>(userInfoBOS, userInfoPageDTO.getPageNo(), pageListUserInfos.getTotal(), userInfoPageDTO.getPageSize());
    }
}
