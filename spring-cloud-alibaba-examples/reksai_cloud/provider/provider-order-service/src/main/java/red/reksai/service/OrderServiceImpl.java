package red.reksai.service;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;
import service.IUserService;
import service.IOrderService;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-02-15 14:22
 * @since :
 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Reference
    private IUserService iUserService ;


}
