package red.reksai;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 认证服务器启动类
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-06-08 22:39
 * @since :
 */
@MapperScan(basePackages="red.reksai.server.mapper")
@SpringBootApplication
public class Oauth2ServerByRBACApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ServerByRBACApplication.class, args);
    }

}
