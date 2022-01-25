package cn.celess.gums.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 2021/11/23
 * TODO:
 *
 * @author 禾几海
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Pageable implements Serializable {
    private static final long serialVersionUID = -8231258776829592435L;

    private Integer pageNum;
    private Integer pageSize;
}
