package pl.com.app.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.com.app.dto.ChannelDTO;
import pl.com.app.model.Channel;

@Mapper(componentModel = "spring")
public interface ChannelMapper {
    @Mappings({
            @Mapping(source = "category", target = "categoryDTO")
    })
    ChannelDTO channelToChannelDTO(Channel channel);

    @Mappings({
            @Mapping(source = "categoryDTO", target = "category")
    })
    Channel channelDTOToChannel(ChannelDTO channelDTO);
}
