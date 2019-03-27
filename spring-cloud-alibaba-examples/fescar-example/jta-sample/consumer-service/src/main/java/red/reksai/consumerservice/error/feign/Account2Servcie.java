package red.reksai.consumerservice.error.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-03-27 20:40
 * @since :
 */
@FeignClient(value = "account2", url = "http://127.0.0.1:28082")
public interface Account2Servcie {
    @RequestMapping("/account2/add")
    public boolean add();

}
