package cn.celess.dums.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 登录信息表
 * </p>
 *
 * @author 禾几海
 * @since 2022/01/20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("dums_login_history")
public class LoginHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type= IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 服务id
     */
    private Integer serviceId;

    /**
     * 登录平台
     */
    private String platform;

    /**
     * 登录ip
     */
    private String ip;

    /**
     * 登录浏览器
     */
    private String browser;

    /**
     * 创建日期
     */
    private LocalDate createDt;

    /**
     * 备注
     */
    private String remark;


}
