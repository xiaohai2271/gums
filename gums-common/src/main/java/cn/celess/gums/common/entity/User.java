package cn.celess.gums.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author 禾几海
 * @since 2022/01/21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName(value = "gums_user",resultMap = "BaseResultMap")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String phone;

    private Boolean phoneStatus;

    /**
     * 邮箱
     */
    private String email;

    private Boolean emailStatus;


    /**
     * 密码
     */
    private String password;

    /**
     * 关联服务id
     */
    private Integer serviceId;

    /**
     * 创建日期
     */
    private LocalDateTime createDt;

    /**
     * 更新日期
     */
    private LocalDateTime updateDt;

    /**
     * 备注
     */
    private String remark;

    private transient List<Role> roles;
}
