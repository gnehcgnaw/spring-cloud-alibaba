package red.reksai.mq.order;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-02-20 14:24
 * @since :
 */
public class OrderProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("provider-user-service-test-order-producer");
        defaultMQProducer.setNamesrvAddr("127.0.0.1:9876");
        defaultMQProducer.start();
        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};

        for (int i=0 ;i <100 ;i++){
            int orderId = i % 10 ;
            Message message = new Message("TopicTestOrder",tags[i%tags.length],"KEY"+i,("Hello RocketMQ"+i).getBytes(Charset.defaultCharset()));
            /**
             * new MessageQueueSelector()接口其实是就一个内部类，而MessageQueueSelector其实有三个实现：
             * @see org.apache.rocketmq.client.producer.selector.SelectMessageQueueByHash
             * @see org.apache.rocketmq.client.producer.selector.SelectMessageQueueByRandom
             * @see org.apache.rocketmq.client.producer.selector.SelectMessageQueueByMachineRoom#select(List, Message, Object) 返回的是null
             *
             */
            SendResult sendResult = defaultMQProducer.send(message,  new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    Integer id = (Integer) arg;
                    //实现对消息队列的选择，其实就是使用ID对我的队列总数做模运算，默认messageQueue.size为4
                    int index = id % mqs.size();
                    return mqs.get(index);
                }
                //相同的orderId可以进入相同的队列。
            }, orderId);

            System.out.printf("%s%n", sendResult);
        }
        defaultMQProducer.shutdown();
    }
}
