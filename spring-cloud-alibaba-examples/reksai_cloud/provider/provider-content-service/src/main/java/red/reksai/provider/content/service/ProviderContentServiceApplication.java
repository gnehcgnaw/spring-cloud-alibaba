package red.reksai.provider.content.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 *
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-06-08 22:39
 * @since :
 */
@EnableDiscoveryClient
@MapperScan(basePackages = "red.reksai.provider.content.service.mapper")
@SpringBootApplication
public class ProviderContentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderContentServiceApplication.class, args);
    }

}
