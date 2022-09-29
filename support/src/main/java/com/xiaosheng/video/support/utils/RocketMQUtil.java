package com.xiaosheng.video.support.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

@Slf4j
public class RocketMQUtil {
    public static void syncSendMsg(DefaultMQProducer producer, Message msg) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        SendResult result = producer.send(msg);
        log.info("result: {}", result);
    }

    public static void asyncSendMsg(DefaultMQProducer producer, Message msg) throws RemotingException, MQClientException, InterruptedException {
        int messageCount = 2;
        CountDownLatch2 countDownLatch = new CountDownLatch2(messageCount);
        for (int i = 0; i < messageCount; i++) {
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    countDownLatch.countDown();
                    log.info(sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable e) {
                    countDownLatch.countDown();
                    log.error("err happened when send msg{}", e);
                    e.printStackTrace();
                }
            });
        }
    }
}
