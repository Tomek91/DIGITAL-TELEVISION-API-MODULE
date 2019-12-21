package pl.com.app.mappers;

import org.mapstruct.Mapper;
import pl.com.app.dto.CategoryDTO;
import pl.com.app.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO categoryToCategoryDTO(Category category);

    Category categoryDTOToCategory(CategoryDTO categoryDTO);
}
