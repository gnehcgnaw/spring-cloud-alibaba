package red.reksai.message.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-02-15 15:33
 * @since :
 */
public interface MySource {
    @Output("output")
    MessageChannel output();

}
