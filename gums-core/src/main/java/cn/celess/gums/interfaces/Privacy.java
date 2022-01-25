package cn.celess.gums.interfaces;

import org.springframework.beans.BeanUtils;

/**
 * 2021/11/12
 * 标识一个类是否包含个人隐私，并指定隐私处理方案
 *
 * @author 禾几海
 */
public interface Privacy<T> {
    /**
     * 对个人信息进行处理
     *
     * @return 实例
     */
    T handlePrivacy();

    /**
     * 创建拷贝实例
     *
     * @param t     源对象
     * @param clazz 源对象类型
     * @return 拷贝实例
     */
    default T clone(T t, Class<T> clazz) {
        if (t == null) {
            return null;
        }
        T target = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(t, target);
        return target;
    }
}
