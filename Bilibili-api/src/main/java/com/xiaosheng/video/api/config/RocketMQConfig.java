package com.xiaosheng.video.api.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaosheng.video.facade.bo.UserFollowingBO;
import com.xiaosheng.video.facade.constant.UserMomentsConstant;
import com.xiaosheng.video.facade.dto.UserMomentsDTO;
import com.xiaosheng.video.services.service.UserFollowingService;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class RocketMQConfig {
    @Value("${rocketmq.name.server.address}")
    private String nameServerAddr;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private UserFollowingService userFollowingService;

    /**
     * 生产者
     *
     * @return
     * @throws MQClientException
     */
    @Bean("momentsProducer")
    public DefaultMQProducer momentsProducer() throws MQClientException {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer(UserMomentsConstant.GROUP_MOMENTS);
        defaultMQProducer.setNamesrvAddr(nameServerAddr);
        defaultMQProducer.start();
        return defaultMQProducer;
    }

    /**
     * 消费者
     *
     * @return
     * @throws MQClientException
     */
    @Bean("momentsConsumer")
    public DefaultMQPushConsumer momentsConsumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(UserMomentsConstant.GROUP_MOMENTS);
        consumer.setNamesrvAddr(nameServerAddr);
        consumer.subscribe(UserMomentsConstant.TOPIC_MOMENTS, "*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : list) {
                    log.info("msg:{}", msg);
                    if (msg == null) {
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                    String bodyStr = new String(msg.getBody());
                    UserMomentsDTO userMomentsDTO = JSONObject.toJavaObject(JSONObject.parseObject(bodyStr), UserMomentsDTO.class);
                    Long userid = userMomentsDTO.getUserid();
                    List<UserFollowingBO> userFans = userFollowingService.getUserFans(userid.intValue());
                    for (UserFollowingBO fan : userFans) {
                        String key = "subscribed-" + fan.getUserid();
                        String subscribedListStr = redisTemplate.opsForValue().get(key);
                        List<UserMomentsDTO> subscribedList = new ArrayList<>();
                        if (!StringUtil.isNullOrEmpty(subscribedListStr)) {
                            JSONArray.parseArray(subscribedListStr, UserMomentsDTO.class);
                        }
                        subscribedList.add(userMomentsDTO);
                        redisTemplate.opsForValue().set(key, JSONObject.toJSONString(subscribedList));
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        return consumer;
    }
}
