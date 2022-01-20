package cn.celess.dums.service.impl;

import cn.celess.dums.entity.Service;
import cn.celess.dums.mapper.ServiceMapper;
import cn.celess.dums.service.ServiceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务信息表 服务实现类
 * </p>
 *
 * @author 禾几海
 * @since 2022-01-20
 */
@Service
public class ServiceServiceImpl extends ServiceImpl<ServiceMapper, Service> implements ServiceService {

}
