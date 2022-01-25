package cn.celess.gums.convert;

import cn.celess.gums.common.entity.User;
import cn.celess.gums.vo.CommonUserVO;
import cn.celess.gums.vo.LoginUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
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
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    @Mappings({
            @Mapping(target = "phoneVerified", source = "phoneStatus")
    })
    CommonUserVO toCommonUserVO(User user);

    @Mappings({})
    LoginUserVO toVo(User user);
}
