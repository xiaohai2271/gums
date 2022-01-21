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
 * 服务信息表
 * </p>
 *
 * @author 禾几海
 * @since 2022/01/20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("dums_mservice")
public class Mservice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 服务名称
     */
    private String serviceName;

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


}
