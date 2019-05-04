package red.reksai.providergithubservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ProviderGithubServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProviderGithubServiceApplication.class, args);
	}

}
