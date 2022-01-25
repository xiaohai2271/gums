package cn.celess.gums.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 服务信息表
 * </p>
 *
 * @author 禾几海
 * @since 2022/01/21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("gums_mservice")
public class Mservice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 服务名称
     */
    private String serviceName;

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


}
