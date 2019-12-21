package pl.com.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.app.dto.CategoryDTO;
import pl.com.app.exceptions.ExceptionCode;
import pl.com.app.exceptions.MyException;
import pl.com.app.mappers.CategoryMapper;
import pl.com.app.model.Category;
import pl.com.app.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    public List<CategoryDTO> findAll() {
        try {
            return categoryRepository
                    .findAll()
                    .stream()
                    .map(categoryMapper::categoryToCategoryDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, e.getMessage());
        }
    }

    public CategoryDTO findOne(Long id) {
        try {
            if (id == null) {
                throw new NullPointerException("CATEGORY ID IS NULL");
            }

            return categoryRepository
                    .findById(id)
                    .map(categoryMapper::categoryToCategoryDTO)
                    .orElseThrow(NullPointerException::new);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, e.getMessage());
        }
    }

    public CategoryDTO add(CategoryDTO categoryDTO) {

        try {
            if (categoryDTO == null) {
                throw new NullPointerException("CATEGORY IS NULL");
            }

            Category categoryFromDb = categoryRepository.save(categoryMapper.categoryDTOToCategory(categoryDTO));
            return categoryMapper.categoryToCategoryDTO(categoryFromDb);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, e.getMessage());
        }
    }

    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        try {
            if (id == null) {
                throw new NullPointerException("ID IS NULL");
            }
            if (categoryDTO == null) {
                throw new NullPointerException("CATEGORY IS NULL");
            }

            Category category = categoryRepository.findById(id).orElseThrow(NullPointerException::new);

            category.setName(categoryDTO.getName() == null ? category.getName() : categoryDTO.getName());

            Category categoryAfterUpdate = categoryRepository.save(category);
            return categoryMapper.categoryToCategoryDTO(categoryAfterUpdate);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, e.getMessage());
        }
    }

    public CategoryDTO delete(Long id) {

        try {
            if (id == null) {
                throw new NullPointerException("CATEGORY ID IS NULL");
            }
            categoryRepository.deleteById(id);

            Category categoryToDelete = categoryRepository.findById(id)
                    .orElseThrow(NullPointerException::new);
            categoryRepository.delete(categoryToDelete);
            return categoryMapper.categoryToCategoryDTO(categoryToDelete);


        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, e.getMessage());
        }
    }
}
