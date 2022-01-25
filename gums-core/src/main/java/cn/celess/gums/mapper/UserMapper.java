package cn.celess.gums.mapper;

import cn.celess.gums.dto.UserPageQueryDto;
import cn.celess.gums.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author 禾几海
 * @since 2022/01/20
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 分页查询
     *
     * @param page     分页对象
     * @param queryDto 查询条件
     * @return 分页结果
     */
    IPage<User> pageQuery(Page<?> page, @Param("query") UserPageQueryDto queryDto);
}
