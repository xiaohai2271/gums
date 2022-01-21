package cn.celess.dums.convert;

import org.springframework.beans.BeanUtils;

/**
 * 2021/11/23
 * TODO:
 *
 * @author 禾几海
 */
public abstract class AbstractConverter {
    @SuppressWarnings("unchecked")
    public static <R> R clone(R source) {
        if (source == null) {
            return null;
        }
        return (R) convertInternal(source, BeanUtils.instantiateClass(source.getClass()));
    }

    public static <R, T> T convert(R source, Class<T> targetType) {
        if (source == null) {
            return null;
        }
        return convertInternal(source, BeanUtils.instantiateClass(targetType));
    }

    public static <R, T> T convertInternal(R source, T target) {
        if (target == null) {
            return null;
        }
        BeanUtils.copyProperties(source, target);
        return target;
    }

}
