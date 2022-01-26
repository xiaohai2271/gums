package cn.celess.gums.dto;

import cn.celess.gums.common.page.Pageable;
import lombok.Data;

import java.time.LocalDateTime;

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

    private String email;

    private Boolean phoneStatus;

    private Boolean emailStatus;
    /**
     * 账户账号
     */
    private String username;

    private String remark;

    private Integer serviceId;

    private LocalDateTime createDtStart;

    private LocalDateTime createDtEnd;

    private Pageable pageable;
}
