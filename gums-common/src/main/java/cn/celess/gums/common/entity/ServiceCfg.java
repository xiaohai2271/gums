package cn.celess.gums.common.entity;

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
 * 服务配置信息表
 * </p>
 *
 * @author 禾几海
 * @since 2022/01/21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("gums_service_cfg")
public class ServiceCfg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 服务表id
     */
    private String serviceId;

    /**
     * 配置信息主键
     */
    private String cfgKey;

    /**
     * 配置信息值
     */
    private String cfgValue;

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
