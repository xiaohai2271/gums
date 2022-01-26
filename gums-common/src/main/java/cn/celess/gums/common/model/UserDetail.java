package cn.celess.gums.common.model;

import cn.celess.gums.common.entity.LoginHistory;
import cn.celess.gums.common.entity.Permission;
import cn.celess.gums.common.entity.Role;
import cn.celess.gums.common.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>date: 2022/01/20</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class UserDetail {
    private User user;

    private LoginHistory loginHistory;

    private List<Permission> allPermissions;

    private List<Role> roles;
}
