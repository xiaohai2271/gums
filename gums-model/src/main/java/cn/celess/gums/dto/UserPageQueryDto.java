package cn.celess.gums.dto;

import cn.celess.gums.page.Pageable;
import lombok.Data;

/**
 * 2021/12/04
 *
 * @author 禾几海
 */
@Data
public class UserPageQueryDto {
    /**
     * id
     */
    private Integer id;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 账户账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    private Pageable pageable;
}
