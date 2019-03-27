package red.reksai.account1service.error.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import red.reksai.account1service.error.service.Account1Service;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-03-27 20:19
 * @since :
 */
@RestController
public class Account1Controller {

    private Account1Service account1Service ;

    public Account1Controller(Account1Service account1Service) {
        this.account1Service = account1Service;
    }

    @GetMapping("/account1/sub")
    public boolean sub(){
        return account1Service.update();
    }
}
