package cn.celess.gums.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
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

    public static <T> PageVO<T> of(IPage<T> page) {
        PageVO<T> pageVO = new PageVO<>(page.getTotal(), page.getRecords());
        pageVO.setPageNum(page.getCurrent())
                .setPageNum(page.getSize());
        return pageVO;
    }
}
