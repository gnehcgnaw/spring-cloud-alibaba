package red.reksai.mq.order;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-02-20 14:25
 * @since :
 */
@Slf4j
public class OrderConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("provider-user-service-test-order-producer");
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        defaultMQPushConsumer.setNamesrvAddr("127.0.0.1:9876");
        defaultMQPushConsumer.subscribe("TopicTestOrder","TagA || TagC || TagD");
        /**
         * 如果是顺序消费，使用的监听是{@link MessageListenerOrderly},返回值为{@link ConsumeOrderlyStatus}，
         *      如果是单实例进行消费，就是单线程线程的，每一个队列有对应的一个线程去消费。
         *      如果是多实例进行消费，默认就是20个线程。
         */
        defaultMQPushConsumer.registerMessageListener(new MessageListenerOrderly() {

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                /**
                 * 默认情况下{@link ConsumeOrderlyContext#autoCommit 为true}
                 */
                context.setAutoCommit(true);
                try{
                    for (int i = 0; i <msgs.size() ; i++) {
                       // System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs.get(i));
                        log.info("msg content {} ,queueId is {}",new String(msgs.get(i).getBody()),msgs.get(i).getQueueId());
                    }
                    return ConsumeOrderlyStatus.SUCCESS;
                }catch (Exception ex){
                    log.debug("ex is {}",ex.getMessage());
                    context.setSuspendCurrentQueueTimeMillis(1000);
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
            }
        });

        defaultMQPushConsumer.start();
        log.info("start");

    }
}
