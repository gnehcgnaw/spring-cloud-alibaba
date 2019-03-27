package red.reksai.account2service.error.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import red.reksai.account2service.error.service.Account2Servcie;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-03-27 17:01
 * @since :
 */
@RestController
public class Account2Controller {
    private Account2Servcie account2Servcie ;

    public Account2Controller(Account2Servcie account2Servcie) {
        this.account2Servcie = account2Servcie;
    }

    @GetMapping("/account2/add")
    public boolean add(){
        return account2Servcie.update();
    }

}
