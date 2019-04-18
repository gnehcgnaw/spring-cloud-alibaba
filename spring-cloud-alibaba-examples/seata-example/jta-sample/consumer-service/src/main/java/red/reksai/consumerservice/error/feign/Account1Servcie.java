package red.reksai.consumerservice.error.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-03-27 20:40
 * @since :
 */
@FeignClient(value = "account1",url = "http://127.0.0.1:28081")
public interface Account1Servcie {
    @RequestMapping("/account1/sub")
    public boolean sub();

}
