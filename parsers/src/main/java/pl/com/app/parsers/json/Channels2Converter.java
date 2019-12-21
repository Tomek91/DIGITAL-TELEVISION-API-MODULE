package pl.com.app.parsers.json;

import pl.com.app.dto.Channel2DTO;

import java.util.List;

public class Channels2Converter extends JsonConverter<List<Channel2DTO>> {
    public Channels2Converter(String jsonFilename) {
        super(jsonFilename);
    }
}
