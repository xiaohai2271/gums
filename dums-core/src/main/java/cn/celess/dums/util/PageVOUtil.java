package cn.celess.dums.util;



import cn.celess.dums.page.PageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 2021/11/23
 * 生成PageVO的util
 *
 * @author 禾几海
 */
public class PageVOUtil {
    public static <T> PageVO<T> of(List<T> list, Integer pageNum, Integer pageSize, Long total) {
        PageVO<T> vo = new PageVO<>(total, list);
        vo.setPageSize(pageSize).setPageNum(pageNum);
        return vo;
    }

    public static <T, R> PageVO<T> of(Page<R> page, Function<R, T> itemConverter) {
//        List<T> list = page.getResult().stream().map(itemConverter).collect(Collectors.toList());
//        return of(list, page.getPageNum(), page.getPageSize(), page.getTotal());
        // todo::
        return null;
    }

    public static <T> PageVO<T> of(Page<T> page) {
        return of(page, t -> t);
    }

    public static PageVO<Void> emptyPage() {
        return new PageVO<Void>(0L, new ArrayList<>());
    }

}
