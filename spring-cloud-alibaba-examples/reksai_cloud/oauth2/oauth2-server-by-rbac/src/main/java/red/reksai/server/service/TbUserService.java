package red.reksai.server.service;

import red.reksai.server.daomain.TbUser;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 *
 *
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-06-09 12:27
 * @since : 
 */
public interface TbUserService extends IService<TbUser>{

    TbUser getByUsername(String username);
}
