package pl.com.app.parsers.json;

import pl.com.app.dto.CategoryDTO;

import java.util.List;

public class CategoriesConverter extends JsonConverter<List<CategoryDTO>> {
    public CategoriesConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
