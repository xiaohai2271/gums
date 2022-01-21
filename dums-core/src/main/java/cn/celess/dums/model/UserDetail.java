package cn.celess.dums.model;

import cn.celess.dums.entity.LoginHistory;
import cn.celess.dums.entity.Permission;
import cn.celess.dums.entity.Role;
import cn.celess.dums.entity.User;
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
