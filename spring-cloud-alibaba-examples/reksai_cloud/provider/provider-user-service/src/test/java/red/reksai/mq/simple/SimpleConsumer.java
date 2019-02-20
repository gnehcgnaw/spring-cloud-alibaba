package red.reksai.mq.simple;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;

import java.nio.charset.StandardCharsets;

/**
 * 简单消费者
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-02-18 13:43
 * @since :
 */
@Slf4j
public class SimpleConsumer {

    public static void main(String [] args) throws MQClientException {
        /**
         * {@link DefaultMQPushConsumer}    从已经注册的消息列表中消费数据
         */
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("provider-user-service-test-simple-consumer");
        defaultMQPushConsumer.setNamesrvAddr("localhost:9876");
        //设置客户端第一次启动开始消费的位置
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //订阅topic,可有匹配tag
        defaultMQPushConsumer.subscribe("test-topic","*");
        //设置消息批处理的最大拉取数量，不一定每次都是设置的最大消息数量，而是随机的
        //defaultMQPushConsumer.setConsumeMessageBatchMaxSize(10);
        defaultMQPushConsumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            try {
                /**
                 *  int i = 1/0;
                 *
                 *  模拟业务出错，这样消息其实也没有被消费
                 */
                if (new String(msgs.get(0).getBody(),StandardCharsets.UTF_8).equals("simple-produce-4")){
                    int i =1/0 ;
                }
                log.info("一次性拉取消息的条数是：{}",msgs.size());
                log.info("具体的消费情况：{}",msgs);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }catch (Exception ex){
                int reconsumeTimes = msgs.get(0).getReconsumeTimes();
                log.debug("ex is {} and msg's reconsumerTimes is {}" ,ex.getMessage(),reconsumeTimes);
                if (reconsumeTimes==3){
                    log.debug("data is record to mysql ");
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS ;
                }
                return ConsumeConcurrentlyStatus.RECONSUME_LATER ;
            }
        });

        defaultMQPushConsumer.start();
        log.info("{}","is run");
    }
}
