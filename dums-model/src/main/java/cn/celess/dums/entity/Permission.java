package cn.celess.dums.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author 禾几海
 * @since 2022-01-20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("dums_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 权限编码
     */
    private String permissionCode;

    /**
     * 权限名称
     */
    private String permissionName;

    private Integer serviceId;

    /**
     * 创建日期
     */
    private LocalDate createDt;

    /**
     * 备注
     */
    private String remark;


}
