package cn.celess.gums.service.impl;

import cn.celess.gums.entity.LoginHistory;
import cn.celess.gums.mapper.LoginHistoryMapper;
import cn.celess.gums.service.LoginHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录信息表 服务实现类
 * </p>
 *
 * @author 禾几海
 * @since 2022/01/20
 */
@Service
public class LoginHistoryServiceImpl extends ServiceImpl<LoginHistoryMapper, LoginHistory> implements LoginHistoryService {

}
