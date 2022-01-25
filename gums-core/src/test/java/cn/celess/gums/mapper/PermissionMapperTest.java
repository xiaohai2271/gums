package cn.celess.gums.mapper;

import cn.celess.gums.BaseTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>date: 2022/01/20</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
class RoleMapperTest extends BaseTest {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserMapper userMapper;

    @Test
    public void Test() {
        roleMapper.selectList(null).forEach(role -> {
            assertNotNull(role.getPermissions());
            assertTrue(role.getPermissions().size() > 0);
            System.out.println(role);
        });

        userMapper.selectList(null).forEach(user -> {
            assertNotNull(user.getRoles());
            assertTrue(user.getRoles().size() > 0);
            System.out.println(user);
        });
    }
}