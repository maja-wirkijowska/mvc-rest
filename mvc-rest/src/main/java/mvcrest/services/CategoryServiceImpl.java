package mvcrest.services;

import mvcrest.api.v1.mappers.CategoryMapper;
import mvcrest.api.v1.models.CategoryDTO;
import mvcrest.repos.CategoryRepo;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepo categoryRepo) {
        this.categoryMapper = categoryMapper;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepo.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        return categoryMapper.categoryToCategoryDTO(categoryRepo.findByName(name));
    }
}
