package red.reksai.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import red.reksai.server.daomain.TbUser;
import red.reksai.server.mapper.TbUserMapper;
import red.reksai.server.service.TbUserService;
/**
 *
 *
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-06-09 12:27
 * @since : 
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements TbUserService{

    @Override
    public TbUser getByUsername(String username) {
        Wrapper<TbUser> queryWrapper = new QueryWrapper<>();
        ((QueryWrapper<TbUser>) queryWrapper).eq("username",username);
        return baseMapper.selectOne(queryWrapper);
    }
}
