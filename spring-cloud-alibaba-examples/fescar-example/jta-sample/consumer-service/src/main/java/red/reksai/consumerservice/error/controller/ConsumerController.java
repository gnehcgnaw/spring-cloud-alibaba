package red.reksai.consumerservice.error.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import red.reksai.consumerservice.error.feign.Account1Servcie;
import red.reksai.consumerservice.error.feign.Account2Servcie;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-03-27 20:34
 * @since :
 */
@RestController
public class ConsumerController {

    private Account1Servcie account1Servcie ;
    private Account2Servcie account2Servcie ;

    public ConsumerController(Account1Servcie account1Servcie, Account2Servcie account2Servcie) {
        this.account1Servcie = account1Servcie;
        this.account2Servcie = account2Servcie;
    }

    @GetMapping("/consumer")
    public boolean consumer(){
        boolean sub = account1Servcie.sub();
        if (!sub){
            throw new RuntimeException();
        }
        return  true ;
    }
}
