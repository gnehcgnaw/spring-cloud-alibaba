package red.reksai.server.service;

import red.reksai.server.daomain.TbPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 *
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-06-09 12:27
 * @since : 
 */
public interface TbPermissionService extends IService<TbPermission>{
    List<TbPermission> selectByUserId(Long userId);
}
