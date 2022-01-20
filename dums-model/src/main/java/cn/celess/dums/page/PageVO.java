package cn.celess.dums.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 2021/11/23
 * TODO:
 *
 * @author 禾几海
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class PageVO<T> extends Pageable {
    /**
     * 总条数
     */
    private Long total;
    /**
     * 数据List
     */
    private List<T> list;
}
