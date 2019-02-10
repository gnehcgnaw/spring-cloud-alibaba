package red.reksai.service;

import com.alibaba.dubbo.config.annotation.Service;
import service.IUserService;

import javax.ws.rs.*;

/**
 * @author : gnehcgnaw
 * @since : 2019-02-10 21:54
 */
@Service(version = "0.0.1")
@Path("user")
public class UserServiceImpl  implements IUserService {

    @GET
    @Path("/add")
    @Override
    public int add(@QueryParam("x") int x, @QueryParam("y") int y){
        return x+y;
    }
}
