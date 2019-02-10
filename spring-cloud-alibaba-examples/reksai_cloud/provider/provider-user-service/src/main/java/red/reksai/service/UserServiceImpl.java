package red.reksai.service;

import com.alibaba.dubbo.config.annotation.Service;
import service.IUserService;

/**
 * @author : gnehcgnaw
 * @since : 2019-02-10 21:54
 */
@Service(version = "0.0.1")
public class UserServiceImpl  implements IUserService {

    @Override
    public int add(int x, int y){
        return x+y;
    }
}
