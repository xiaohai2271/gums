package cn.celess.dums.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDate;
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
 * @since 2022/01/20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName(value = "dums_user",resultMap = "BaseResultMap")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

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
    private LocalDate createDt;

    /**
     * 更新日期
     */
    private LocalDate updateDt;

    /**
     * 备注
     */
    private String remark;

    private transient List<Role> roles;
}
