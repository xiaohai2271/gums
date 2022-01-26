package cn.celess.gums.util;

import cn.celess.gums.convert.AbstractConverter;
import cn.celess.gums.interfaces.Privacy;
import cn.celess.gums.common.page.PageVO;
import cn.celess.gums.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 2021/12/20
 *
 * @author 禾几海
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class PrivacyConverterUtil {

    private static final List<Converter> typeConverters;
    // ** 黑名单重点类型直接返回null
    private static final Set<Class> typeBlacklist;

    static {
        typeConverters = new ArrayList<>();
        typeBlacklist = new HashSet<>();

        typeBlacklist.add(ServletRequest.class);
        typeBlacklist.add(ServletResponse.class);

        typeConverters.add(new Converter(Response.class, PrivacyConverterUtil.responseConverter()));
        typeConverters.add(new Converter(PageVO.class, PrivacyConverterUtil.pageVOConverter()));
        typeConverters.add(new Converter(List.class, PrivacyConverterUtil.listConverter()));
        typeConverters.add(new Converter(Object[].class, PrivacyConverterUtil.objectArrayConverter()));
        typeConverters.add(new Converter(Object.class, PrivacyConverterUtil.objectConverter()));
    }

    /*
     * 转换逻辑：
     * - 非项目内文件，不进行转换，直接返回null
     * - 对于类型为Privacy的参数，进行数据转换，调用handlePrivacy 方法
     * - 对于类型不是Privacy的参数，直接返回该数据
     */
    private static <T> T converter(T t) {
        if (!t.getClass().getPackageName().startsWith("cn.hengyangtec.mr")) {
            // 非mr包的类型，输出t
            return t;
        }
        if (Privacy.class.isAssignableFrom(t.getClass())) {
            Privacy<T> privacy = (Privacy<T>) t;
            return privacy.handlePrivacy();
        }
        return t;
    }

    private static Function<Response<Object>, Response<Object>> responseConverter() {
        return r -> AbstractConverter.clone(r).setData(handlePrivacy(r.getData()));
    }

    private static <T> Function<PageVO<T>, PageVO<T>> pageVOConverter() {
        return r -> AbstractConverter.clone(r).setList(handlePrivacy(r.getList()));
    }

    private static Function<Object, Object> objectConverter() {
        return PrivacyConverterUtil::converter;
    }

    private static Function<List<Object>, List<Object>> listConverter() {
        return r -> List.copyOf(r).stream().map(PrivacyConverterUtil::handlePrivacy).collect(Collectors.toList());
    }

    private static Function<Object[], Object[]> objectArrayConverter() {
        return r -> Arrays.stream(r).map(PrivacyConverterUtil::handlePrivacy).toArray();
    }

    /**
     * 处理脱敏数据
     *
     * @param arg 待脱敏/普通数据
     * @return 脱敏/普通数据
     */
    public static <T> T handlePrivacy(T arg) {
        if (arg == null) {
            return null;
        }
        if (typeBlacklist.stream().anyMatch(cla -> cla.isAssignableFrom(arg.getClass()))) {
            // !ATTENTION:! 黑名单直接返回类型的simpleName,不符合入<T>出<T>的泛型要求，
            // 但鉴于泛型都会被擦除，所以这里直接返回类型的simpleName
            return (T) ("@" + arg.getClass().getSimpleName());
        }
        for (Converter converter : typeConverters) {
            if (converter.clazz.isAssignableFrom(arg.getClass())) {
                return (T) converter.action.apply(arg);
            }
        }
        return arg;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    private static class Converter<T> {
        private Class<T> clazz;
        private Function<T, T> action;
    }

}
