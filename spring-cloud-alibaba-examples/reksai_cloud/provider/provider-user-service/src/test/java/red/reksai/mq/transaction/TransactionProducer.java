package red.reksai.mq.transaction;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.Charset;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-02-20 10:00
 * @since :
 */
public class TransactionProducer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        TransactionMQProducer transactionMQProducer = new TransactionMQProducer("provider-user-service-test-transaction-producer");
        transactionMQProducer.setNamesrvAddr("127.0.0.1:9876");

        TransactionListener transactionListener = new TransactionListenerImpl();
        transactionMQProducer.setTransactionListener(transactionListener);
        transactionMQProducer.start();
        String[] tags = new String[]{"TagA","TagB","TagC","TagD","TagE"};
        for (int i = 0; i < 10 ; i++) {
            Message msg = new Message("TopicTestTransaction",tags[i%tags.length],"KEY"+i,("Hello RocketMQ"+i).getBytes(Charset.defaultCharset()));
            TransactionSendResult transactionSendResult = transactionMQProducer.sendMessageInTransaction(msg, null);
            System.out.printf("%s%n",transactionSendResult);

            Thread.sleep(10);
        }

        for (int i = 0; i < 100000 ; i++) {
            Thread.sleep(1000);
        }

        transactionMQProducer.shutdown();
    }
}

@Slf4j
class TransactionListenerImpl implements TransactionListener{
    /**
     * 当send transactional prepare（half）消息成功时，将调用此方法执行本地事务。
     * @param msg
     * @param arg
     * @return  {@link LocalTransactionState#ROLLBACK_MESSAGE,
     *           @link LocalTransactionState#COMMIT_MESSAGE,
     *           @link LocalTransactionState#UNKNOW}
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        /**
         * 执行本地事务,然后返回执行结果
         */

        return LocalTransactionState.UNKNOW;
    }

    /**
     * 当没有响应准备（一半）消息。经纪人将发送检查消息以检查交易状态，这一点将调用方法来获取本地事务状态。
     * @param msg
     * @return
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        /**
         * 回调检查逻辑
         */
        String transactionId = msg.getTransactionId();

        long preparedTransactionOffset = msg.getPreparedTransactionOffset();
        log.debug("transactionStateId is {} ,and preparedTransactionOffset is {} ",transactionId,preparedTransactionOffset);
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}