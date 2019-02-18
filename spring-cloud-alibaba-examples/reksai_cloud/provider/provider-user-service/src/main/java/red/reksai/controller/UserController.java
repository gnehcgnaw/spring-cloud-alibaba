package red.reksai.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import red.reksai.service.UserService;
import service.IUserService;

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


    private IUserService iUserService ;

    private UserService userService ;
    public UserController(IUserService iUserService , UserService userService) {
        this.iUserService = iUserService;
        this.userService = userService ;
    }

    @GetMapping("/get-info")
    public Map getInfo(){
        Map map = new HashMap<String,Object>();
        return map ;
    }

    @DeleteMapping("/del/{id}")
    public int delUser(@PathVariable("id") Long id){
        int count = userService.delUser(id);
        return count ;
    }
}
