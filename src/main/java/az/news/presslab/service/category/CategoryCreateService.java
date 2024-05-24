package az.news.presslab.service.category;

import az.news.presslab.entity.CategoryEntity;
import az.news.presslab.entity.MainCategoryEntity;
import az.news.presslab.mapper.CategoryMapper;
import az.news.presslab.repository.CategoryRepository;
import az.news.presslab.repository.MainCategoryRepository;
import az.news.presslab.request.CategoryCreateRequest;
import org.springframework.stereotype.Service;

@Service
public class CategoryCreateService {
    private final CategoryRepository categoryRepository;
    private final MainCategoryRepository mainCategoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryCreateService(CategoryRepository categoryRepository, MainCategoryRepository mainCategoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.mainCategoryRepository = mainCategoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryEntity createCategory(CategoryCreateRequest request) {
        MainCategoryEntity mainCategory = mainCategoryRepository.findById(request.getMainCategoryId()).orElseThrow(() -> new IllegalArgumentException("Invalid main category ID"));
        CategoryEntity category = categoryMapper.toCategoryEntity(request,mainCategory);
        return categoryRepository.save(category);
    }
}