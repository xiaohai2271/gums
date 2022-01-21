package cn.celess.dums.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 服务配置信息表
 * </p>
 *
 * @author 禾几海
 * @since 2022/01/20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName(value = "dums_role", resultMap = "BaseResultMap")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 父角色id
     */
    private Integer pRoleId;

    /**
     * 服务id
     */
    private Integer serviceId;

    /**
     * 创建日期
     */
    private LocalDate createDt;

    /**
     * 更新日期
     */
    private LocalDate updateDt;

    /**
     * 备注
     */
    private String remark;

    private transient List<Permission> permissions;
}
