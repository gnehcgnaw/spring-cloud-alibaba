package red.reksai.service.rpc;

import com.alibaba.dubbo.config.annotation.Service;
import entity.User;
import service.IUserService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-02-15 15:10
 * @since :
 */
@Service(version = "0.0.1")
@Path("user")
public class UserServiceRpcImpl  implements IUserService {

    @GET
    @Path("/add")
    @Override
    public int add(@QueryParam("x") int x, @QueryParam("y") int y){
        return x+y;
    }

    @Override
    public User delUser(Integer id) {
        return null;
    }
}