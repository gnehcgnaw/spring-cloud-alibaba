package red.reksai.server.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import red.reksai.server.daomain.TbPermission;
import red.reksai.server.mapper.TbPermissionMapper;
import red.reksai.server.service.TbPermissionService;
/**
 *
 *
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-06-09 12:27
 * @since : 
 */
@Service
public class TbPermissionServiceImpl extends ServiceImpl<TbPermissionMapper, TbPermission> implements TbPermissionService{

    @Override
    public List<TbPermission> selectByUserId(Long userId) {
        return baseMapper.selectByUserId(userId);
    }
}
