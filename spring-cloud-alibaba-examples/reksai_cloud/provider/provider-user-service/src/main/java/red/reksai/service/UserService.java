package red.reksai.service;

import entity.User;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import red.reksai.mapper.UserMapper;

import java.util.HashMap;
import java.util.Map;


/**
 * @author : gnehcgnaw
 * @since : 2019-02-10 21:54
 */
@Service
public class UserService {
    @Autowired
    private MessageChannel output;

    @Autowired
    private UserMapper userMapper ;

    public int delUser(Long id) {
        User user = userMapper.selectById(id);
        if (user!=null){
            if (send(id)){
                user.setIsUsing(false);
                int i = userMapper.updateById(user);
                return i ;
            }
        }else{
            return 0 ;
        }
        return 0 ;
    }

    public boolean send(Long id){
        Map<String, Object>  headers= new HashMap<>();
        headers.put(MessageConst.PROPERTY_KEYS,"tagObject");
        Message<?> message = MessageBuilder.createMessage(id,new MessageHeaders(headers));
        return output.send(message);
    }

}
