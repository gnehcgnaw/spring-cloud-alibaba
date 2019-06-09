package red.reksai.server.daomain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

@TableName(value = "tb_role_permission")
public class TbRolePermission implements Serializable {
     @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色 ID
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 权限 ID
     */
    @TableField(value = "permission_id")
    private Long permissionId;

    private static final long serialVersionUID = 1L;

    public static final String COL_ROLE_ID = "role_id";

    public static final String COL_PERMISSION_ID = "permission_id";

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取角色 ID
     *
     * @return role_id - 角色 ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色 ID
     *
     * @param roleId 角色 ID
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取权限 ID
     *
     * @return permission_id - 权限 ID
     */
    public Long getPermissionId() {
        return permissionId;
    }

    /**
     * 设置权限 ID
     *
     * @param permissionId 权限 ID
     */
    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}