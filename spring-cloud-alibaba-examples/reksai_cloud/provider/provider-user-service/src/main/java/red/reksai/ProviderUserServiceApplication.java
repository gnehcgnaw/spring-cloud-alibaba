package red.reksai;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import red.reksai.message.source.MySource;

/**
 * @author : gnehcgnaw
 * @since  : 2018-12-07 11:09
 */

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("red.reksai.mapper")
@EnableBinding(MySource.class)
public class ProviderUserServiceApplication {

    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }
    public static void main(String[] args) {
        SpringApplication.run(ProviderUserServiceApplication.class, args);
    }

}
