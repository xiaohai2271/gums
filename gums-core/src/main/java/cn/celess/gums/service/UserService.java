package cn.celess.gums.service;

import cn.celess.gums.dto.UserLoginDto;
import cn.celess.gums.dto.UserPageQueryDto;
import cn.celess.gums.dto.UserRegDto;
import cn.celess.gums.dto.UserResetPwdDto;
import cn.celess.gums.common.entity.User;
import cn.celess.gums.common.page.PageVO;
import cn.celess.gums.vo.CommonUserVO;
import cn.celess.gums.vo.LoginUserVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author 禾几海
 * @since 2022/01/20
 */
public interface UserService extends IService<User> {

    /**
     * 登录
     *
     * @param loginDto 登录信息
     * @return 用户信息
     */
    LoginUserVO login(UserLoginDto loginDto);


    /**
     * 注册
     *
     * @param regDto 注册信息
     * @return 用户信息
     */
    CommonUserVO registration(UserRegDto regDto);


    /**
     * 注销本次登录
     * 正常返回为注销成功
     */
    void logout();

    /**
     * 获取当前登录用户的信息
     *
     * @return 用户信息
     */
    CommonUserVO getUserInfo();


    /**
     * 重置用户密码
     */
    void resetPassword(UserResetPwdDto userRegDto);


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CommonUserVO queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    PageVO<CommonUserVO> pageQuery(UserPageQueryDto userPageQueryDto);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    CommonUserVO insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    CommonUserVO update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
}
