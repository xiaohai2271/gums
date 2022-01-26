package cn.celess.gums.common.utils;


import cn.celess.gums.common.page.PageVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 2021/11/23
 * PageVO的util
 *
 * @author 禾几海
 */
public class PageVOUtil {
    public static <T, R> PageVO<T> of(Page<R> page, Function<R, T> itemConverter) {
        IPage<T> list = page.convert(itemConverter);
        return PageVO.of(list);
    }

    public static <T> PageVO<T> emptyPage() {
        return new PageVO<T>(0L, new ArrayList<>());
    }

}
