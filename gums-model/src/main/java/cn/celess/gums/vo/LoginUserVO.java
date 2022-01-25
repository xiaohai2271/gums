package cn.celess.gums.vo;

import cn.celess.gums.common.entity.LoginHistory;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 2021/11/12
 *
 * @author 禾几海
 */
@Data
@Accessors(chain = true)
public class LoginUserVO {
    private static final long serialVersionUID = -1L;

    private Integer id;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 手机号验证状态
     */
    private Boolean phoneStatus;

    private String email;

    private String emailStatus;

    /**
     * 账户
     */
    private String username;

    /**
     * 上次登录信息
     */
    private LoginHistory longinHistory;

    /**
     * 账户创建日期
     */
    private Date createDt;

    /**
     * 账户登录日期
     */
    private Date updateDt;

    private String token;
}
