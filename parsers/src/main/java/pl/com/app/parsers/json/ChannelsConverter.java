package pl.com.app.parsers.json;

import pl.com.app.dto.ChannelDTO;

import java.util.List;

public class ChannelsConverter extends JsonConverter<List<ChannelDTO>> {
    public ChannelsConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
