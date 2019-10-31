package red.reksai.server.config;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 输出加密之后的字符串
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-06-09 01:51
 * @since :
 */
public class PasswordEncoderTest {
    @Test
    public void test(){
        String secret = new BCryptPasswordEncoder().encode("123456");
        System.out.println(secret);
    }
}
