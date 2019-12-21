package pl.com.app.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.com.app.dto.Channel2DTO;
import pl.com.app.model.Channel2;

@Mapper(componentModel = "spring")
public interface Channel2Mapper {
    @Mappings({
            @Mapping(source = "tvPackage", target = "tvPackageDTO"),
            @Mapping(source = "category", target = "categoryDTO")
    })
    Channel2DTO channelToChannelDTO(Channel2 channel);

    @Mappings({
            @Mapping(source = "tvPackageDTO", target = "tvPackage"),
            @Mapping(source = "categoryDTO", target = "category")
    })
    Channel2 channelDTOToChannel(Channel2DTO channelDTO);
}
