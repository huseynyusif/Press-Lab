package az.news.presslab.controller.category;

import az.news.presslab.entity.CategoryEntity;
import az.news.presslab.request.CategoryCreateRequest;
import az.news.presslab.service.category.CategoryCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryCreateController {
    private final CategoryCreateService categoryCreateService;

    @Autowired
    public CategoryCreateController(CategoryCreateService categoryCreateService) {
        this.categoryCreateService = categoryCreateService;
    }

    @PostMapping("/create")
    public CategoryEntity createCategory(@RequestBody CategoryCreateRequest request) {
        return categoryCreateService.createCategory(request);
    }
}