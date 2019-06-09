package red.reksai.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import red.reksai.server.daomain.TbPermission;

import java.util.List;

public interface TbPermissionMapper extends BaseMapper<TbPermission> {

    List<TbPermission> selectByUserId(@Param("userId") Long userId);
}