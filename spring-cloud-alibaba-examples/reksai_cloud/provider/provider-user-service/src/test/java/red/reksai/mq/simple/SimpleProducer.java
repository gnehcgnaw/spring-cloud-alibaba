package red.reksai.mq.simple;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import java.nio.charset.StandardCharsets;

/**
 * 普通消息
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-02-18 09:56
 * @since :
 */
public class SimpleProducer {

    public  static void main(String [] args) throws MQClientException, InterruptedException, RemotingException, MQBrokerException {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("provider-user-service-test-simple-producer");
        defaultMQProducer.setNamesrvAddr("localhost:9876");
        /**
         * 提供端重试机制：配合{@link DefaultMQProducer#send(Message, long)},进行使用
         */
        defaultMQProducer.setRetryTimesWhenSendFailed(10);
        defaultMQProducer.start();
        for (int i = 0; i < 100; i++) {
            Message message = new Message("test-topic","simple",("simple-produce-"+i).getBytes(StandardCharsets.UTF_8));
            SendResult sendResult = defaultMQProducer.send(message,1000);
            System.out.println(sendResult);
        }
        defaultMQProducer.shutdown();

    }
}
