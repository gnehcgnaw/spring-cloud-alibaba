package red.reksai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 认证服务器启动类
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-06-08 22:39
 * @since :
 */
@SpringBootApplication
public class Oauth2ServerByDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ServerByDbApplication.class, args);
    }

}
