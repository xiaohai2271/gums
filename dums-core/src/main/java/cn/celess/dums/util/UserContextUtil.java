package cn.celess.dums.util;


import cn.celess.dums.entity.Permission;
import cn.celess.dums.model.UserDetail;

/**
 * 2021/12/04
 *
 * @author 禾几海
 */
public class UserContextUtil {
    private static final ThreadLocal<UserDetail> userThreadLocal = new ThreadLocal<>();

    /**
     * 设置用户
     *
     * @param userDetail 用户信息
     */
    public static void setUser(UserDetail userDetail) {
        userDetail.getUser().setRoles(null);
        userThreadLocal.set(userDetail);
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
}
