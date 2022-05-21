package mvcrest.services;

import mvcrest.api.v1.mappers.CategoryMapper;
import mvcrest.api.v1.models.CategoryDTO;
import mvcrest.domain.Category;
import mvcrest.repos.CategoryRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    public static final Long ID = 2L;
    public static final String NAME = "Jimmy";
    CategoryService categoryService;

    @Mock
    CategoryRepo categoryRepo;

    @BeforeEach
    void setUp() throws Exception {
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepo);
    }

    @Test
    void getAllCategories() {
        //given
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());
        when(categoryRepo.findAll()).thenReturn(categories);
        //when
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();
        //then
        assertEquals(3, categoryDTOS.size());
    }

    @Test
    void getCategoryByName() throws Exception {
        //given
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);
        when(categoryRepo.findByName(anyString())).thenReturn(category);
        //when
        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);
        //then
        assertEquals(ID, categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }
}