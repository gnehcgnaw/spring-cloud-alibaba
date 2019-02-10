package red.reksai.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : gnehcgnaw
 * @since  : 2018-12-07 11:51
 */
@RefreshScope
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/get-info")
    public Map getInfo(){
        Map map = new HashMap<String,Object>();
        return map ;
    }
}
