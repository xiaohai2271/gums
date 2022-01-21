package cn.celess.dums.service.impl;

import cn.celess.dums.dto.UserLoginDto;
import cn.celess.dums.dto.UserPageQueryDto;
import cn.celess.dums.dto.UserRegDto;
import cn.celess.dums.dto.UserResetPwdDto;
import cn.celess.dums.entity.User;
import cn.celess.dums.processor.login.LoginProcessorFactory;
import cn.celess.dums.mapper.UserMapper;
import cn.celess.dums.page.PageVO;
import cn.celess.dums.service.UserService;
import cn.celess.dums.util.ValidUtil;
import cn.celess.dums.vo.CommonUserVO;
import cn.celess.dums.vo.LoginUserVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author 禾几海
 * @since 2022/01/20
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private LoginProcessorFactory loginProcessorFactory;


    @Override
    public LoginUserVO login(UserLoginDto loginDto) {
        return loginProcessorFactory.login(loginDto);
    }

    @Override
    public CommonUserVO registration(UserRegDto regDto) {
//        // 参数校验
//        ValidUtil.validRegistrationArgs(regDto);
//        // 校验手机验证码
//        String code = DataProcessorUtil.handlerAndRemoveVerifyCode(regDto);
//        if (!Objects.equals(code, regDto.getCode())) {
//            throw new CommonException(ResponseConstant.WRONG_MOBILE_VERIFY_CODE);
//        }
//        // 校验账号是否已经注册
//        User user = new User();
//        user.setUsername(regDto.getAccount());
//        user = baseMapper.queryByUniqueKey(user);
//        if (user != null) {
//            throw new CommonException(ResponseConstant.ACCOUNT_EXIST);
//        }
//
//        String encryptionPassword = DataProcessorUtil.handlerPassword(regDto);
//
//        User insertUser = new User();
//        insertUser.setUsername(regDto.getAccount());
//        insertUser.setPassword(encryptionPassword);
//        insertUser.setPhone(regDto.getPhone());
//        insertUser.setPhoneVerifyStatus(true);
//        insertUser.setCreateDt(new Date());
//        baseMapper.insert(insertUser);
//
//        // 设置权限
//        UserPermission userPermission = new UserPermission()
//                .setUserId(insertUser.getId())
//                .setPermission(Permission.USER.toString())
//                .setCreateDt(new Date());
//
//        userPermissionDao.insert(userPermission);
//
//        return UserModelConverter.toCommonUserVO(insertUser);
        return null;
    }

    @Override
    public void logout() {
//        User currentUser = UserContextUtil.getUser();
//        if (currentUser == null) {
//            return;
//        }
//        RedisUtil.delete(UserConstant.getCacheNameOfUser(currentUser.getId()));
    }

    @Override
    public CommonUserVO getUserInfo() {
//        User currentUser = UserContextUtil.getUser();
//        if (currentUser == null) {
//            throw new CommonException(ResponseConstant.USER_NOT_LOGIN);
//        }
//        return UserModelConverter.toCommonUserVO(currentUser);
        return null;
    }

    @Override
    public void resetPassword(UserResetPwdDto userRegDto) {
//        ValidUtil.validResetPasswordArgs(userRegDto);
//        // 校验手机验证码
//        String code = DataProcessorUtil.handlerAndRemoveVerifyCode(userRegDto);
//        if (!Objects.equals(code, userRegDto.getCode())) {
//            throw new ArgumentException(ResponseConstant.WRONG_MOBILE_VERIFY_CODE);
//        }
//
//        // 更新用户信息
//        User user = new User();
//        user.setPhone(userRegDto.getPhone());
//
//        user = baseMapper.queryByUniqueKey(user);
//
//        if (user == null) {
//            // 此处不会出现这个错误，因为在发送验证码时就已有校验过手机号是否存在
//            throw new CommonException(ResponseConstant.PHONE_NOT_REGISTERED);
//        }
//
//        // 校验密码是否格式
//        if (!RegexUtil.passwordMatch(AesEncryptUtil.decrypt(userRegDto.getPassword(), user.getAccount()))) {
//            throw new ArgumentException(ResponseConstant.PASSWORD_FORMAT_ERROR);
//        }
//
//        userRegDto.setUsername(user.getAccount());
//        User updateUser = new User();
//        updateUser.setId(user.getId());
//        updateUser.setPassword(DataProcessorUtil.handlerPassword(userRegDto));
//        baseMapper.updateById(updateUser);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CommonUserVO queryById(Integer id) {
        User user = baseMapper.selectById(id);
        return null;
    }


    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public PageVO<CommonUserVO> pageQuery(UserPageQueryDto pageQueryDto) {
//        Pageable pageable = DataProcessorUtil.handlePageable(pageQueryDto.getPageable());
//        PageHelper.startPage(pageable.getPageNum(), pageable.getPageSize());
//        Page<User> users = (Page<User>) baseMapper.queryAll(pageQueryDto);
//        return PageVOUtil.of(users, UserModelConverter::toCommonUserVO);
        return null;
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public CommonUserVO insert(User user) {
        baseMapper.insert(user);
        return null;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public CommonUserVO update(User user) {
        baseMapper.updateById(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return baseMapper.deleteById(id) > 0;
    }
}
