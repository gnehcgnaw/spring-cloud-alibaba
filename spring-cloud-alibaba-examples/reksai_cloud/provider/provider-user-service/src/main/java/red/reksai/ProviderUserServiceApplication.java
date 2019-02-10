package red.reksai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author : gnehcgnaw
 * @since  : 2018-12-07 11:09
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ProviderUserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderUserServiceApplication.class, args);
    }
}
