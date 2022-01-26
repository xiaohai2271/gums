package cn.celess.gums.dto;

import cn.celess.gums.common.entity.Permission;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>date: 2022/01/26</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
@Data
@Accessors(chain = true)
public class PrmSaveDTO {

    private List<Permission> permissions;

    private Integer serviceId;

    private String secretKey;
}
