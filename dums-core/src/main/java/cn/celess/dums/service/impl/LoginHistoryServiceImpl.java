package cn.celess.dums.service.impl;

import cn.celess.dums.entity.LoginHistory;
import cn.celess.dums.mapper.LoginHistoryMapper;
import cn.celess.dums.service.LoginHistoryService;
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
