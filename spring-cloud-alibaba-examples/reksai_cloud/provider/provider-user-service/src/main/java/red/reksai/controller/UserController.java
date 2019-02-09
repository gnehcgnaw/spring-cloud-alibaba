package red.reksai.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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

    @Value("${ext-info-1.user.name}")
    private String name;

    @Value("${ext-info-1.user.id}")
    private String id;

    @GetMapping("/get-info")
    public Map getInfo(){
        Map map = new HashMap<String,Object>();
        map.put("email",email);
        map.put("address",address);
        map.put("name",name);
        map.put("id",id);
        return map ;
    }
}
