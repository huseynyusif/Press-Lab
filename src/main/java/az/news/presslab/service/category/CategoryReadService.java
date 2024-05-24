package az.news.presslab.service.category;

import az.news.presslab.mapper.CategoryMapper;
import az.news.presslab.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import az.news.presslab.response.CategoryReadResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryReadService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryReadService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryReadResponse> getAllCategories() {

        return categoryRepository.findAll().stream()
                .map(categoryMapper::toReadResponse).collect(Collectors.toList());
    }

    public CategoryReadResponse getCategoryById(int id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toReadResponse).orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }
}
