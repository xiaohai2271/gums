package cn.celess.gums.dto;

import cn.celess.gums.common.page.Pageable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>date: 2022/01/26</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
@Data
@Accessors(chain = true)
public class PrmQueryDTO {
    private String name;
    private String value;
    private String type;
    private String description;
    private String remark;
    private String key;
    private Pageable pageable;
}
