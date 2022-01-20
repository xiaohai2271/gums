package cn.celess.dums.service.impl;

import cn.celess.dums.entity.User;
import cn.celess.dums.mapper.UserMapper;
import cn.celess.dums.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author 禾几海
 * @since 2022-01-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
