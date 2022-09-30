package com.xiaosheng.video.services.service;

import com.alibaba.fastjson.JSONObject;
import com.xiaosheng.video.dao.mapper.UserMomentsPOMapper;
import com.xiaosheng.video.dao.po.UserMomentsPO;
import com.xiaosheng.video.facade.bo.UserMomentsBO;
import com.xiaosheng.video.facade.constant.UserMomentsConstant;
import com.xiaosheng.video.facade.dto.UserMomentsDTO;
import com.xiaosheng.video.support.utils.BeanConvertorUtils;
import com.xiaosheng.video.support.utils.RocketMQUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class UserMomentsService {
    @Resource
    private UserMomentsPOMapper userMomentsPOMapper;

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private RedisTemplate redisTemplate;

    public void addUserMoments(UserMomentsDTO userMomentsDTO) {
        UserMomentsPO userMomentsPO = new UserMomentsPO();
        BeanConvertorUtils.copy(userMomentsDTO, userMomentsPO);
        userMomentsPOMapper.insertSelective(userMomentsPO);
        DefaultMQProducer producer = (DefaultMQProducer) applicationContext.getBean("momentsProducer");
        Message msg = new Message(UserMomentsConstant.TOPIC_MOMENTS, JSONObject.toJSONString(userMomentsPO).getBytes(StandardCharsets.UTF_8));
        try {
            RocketMQUtil.syncSendMsg(producer, msg);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("err appened when add usermoments:{}", e.getMessage());
        }
    }

    public List<UserMomentsBO> getUserMoments(Long currentUserId) {
        String key = "subscribed-" + currentUserId;
        List<UserMomentsDTO> list = (List<UserMomentsDTO>) redisTemplate.opsForValue().get(key);
        List<UserMomentsBO> userMomentsBOS = BeanConvertorUtils.copyList(list, UserMomentsBO.class);
        return userMomentsBOS;
    }
}
