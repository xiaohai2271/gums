package cn.celess.dums.vo;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 2021/12/04
 *
 * @author 禾几海
 */
@Data
@Accessors(chain = true)
public class CommonUserVO {
    private static final long serialVersionUID = -1L;

    private Integer id;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 手机号验证状态
     */
    private Boolean phoneVerifyStatus;

    /**
     * 账户
     */
    private String account;

    /**
     * 展示昵称
     */
    private String displayName;

    /**
     * 展示头像
     */
    private String avatar;

    /**
     * 上次登录日期
     */
    private Date lastLoginDt;

    /**
     * 账户创建日期
     */
    private Date createDt;

    /**
     * 账户登录日期
     */
    private Date updateDt;
}
