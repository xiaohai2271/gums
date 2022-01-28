package cn.celess.gums.common.utils;

import cn.celess.gums.common.entity.Permission;
import cn.celess.gums.common.model.UserDetail;
import lombok.NonNull;

/**
 * 2021/12/04
 *
 * @author 禾几海
 */
public class UserContextUtil {
    private static final ThreadLocal<UserDetail> userThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<String> tokenThreadLocal = new ThreadLocal<>();

    /**
     * 设置用户
     *
     * @param userDetail 用户信息
     */
    public static void setUser(@NonNull UserDetail userDetail) {
        userDetail.getUser().setRoles(null);
        userThreadLocal.set(userDetail);
    }

    /**
     * 清除用户
     */
    public static void clear() {
        userThreadLocal.set(null);
    }

    /**
     * 获取用户
     *
     * @return 用户信息
     */
    public static UserDetail getUser() {
        return userThreadLocal.get();
    }

    public static boolean hasPermission(String permissionCode) {
        UserDetail user = getUser();
        for (Permission userPermission : user.getAllPermissions()) {
            if (userPermission.getPermissionCode().equals(permissionCode)) {
                return true;
            }
        }
        return false;
    }

    public static String getToken() {
        return tokenThreadLocal.get();
    }

    public static void setToken(String token) {
        tokenThreadLocal.set(token.trim());
    }
}
