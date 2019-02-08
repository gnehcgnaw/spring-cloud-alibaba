package red.reksai.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: gnehcgnaw
 * @date: 2018-12-07 11:51
 */
@RefreshScope
@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${user.email}")
    private String email;

    @Value("${user.address}")
    private String address;

    @GetMapping("/get-all")
    public String getAll(){
        return email +"\n"+ address;
    }

}
