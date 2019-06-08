package red.reksai.server.config;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-06-09 01:51
 * @since :
 */
public class PasswordEncoderTest {
    @Test
    public void test(){
        String secret = new BCryptPasswordEncoder().encode("secret");
        System.out.println(secret);
    }
}
