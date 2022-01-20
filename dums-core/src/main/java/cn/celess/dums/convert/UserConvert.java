package cn.celess.dums.convert;

import cn.celess.dums.entity.User;
import cn.celess.dums.vo.LoginUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>date: 2022/01/20</P>
 * <p>desc: </p>
 * <p>mail: a@celess.cn</p>
 *
 * @author 禾几海
 */
@Mapper()
public interface UserConvert {
    public final static UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    @Mappings({})
    LoginUserVO toVo(User user);
}
