package red.reksai.server.daomain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

@TableName(value = "tb_role")
public class TbRole implements Serializable {
     @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父角色
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 角色名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 角色英文名称
     */
    @TableField(value = "enname")
    private String enname;

    /**
     * 备注
     */
    @TableField(value = "description")
    private String description;

    @TableField(value = "created")
    private Date created;

    @TableField(value = "updated")
    private Date updated;

    private static final long serialVersionUID = 1L;

    public static final String COL_PARENT_ID = "parent_id";

    public static final String COL_NAME = "name";

    public static final String COL_ENNAME = "enname";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_CREATED = "created";

    public static final String COL_UPDATED = "updated";

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
     * 获取父角色
     *
     * @return parent_id - 父角色
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父角色
     *
     * @param parentId 父角色
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取角色名称
     *
     * @return name - 角色名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名称
     *
     * @param name 角色名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取角色英文名称
     *
     * @return enname - 角色英文名称
     */
    public String getEnname() {
        return enname;
    }

    /**
     * 设置角色英文名称
     *
     * @param enname 角色英文名称
     */
    public void setEnname(String enname) {
        this.enname = enname;
    }

    /**
     * 获取备注
     *
     * @return description - 备注
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置备注
     *
     * @param description 备注
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return updated
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * @param updated
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}