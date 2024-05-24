package az.news.presslab.controller.mainCategory;

import az.news.presslab.entity.MainCategoryEntity;
import az.news.presslab.request.MainCategoryCreateRequest;
import az.news.presslab.service.maincategory.MainCategoryCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main-categories")
public class MainCategoryCreateController {
    private final MainCategoryCreateService mainCategoryCreateService;

    @Autowired
    public MainCategoryCreateController(MainCategoryCreateService mainCategoryCreateService) {
        this.mainCategoryCreateService = mainCategoryCreateService;
    }

    @PostMapping("/create")
    public MainCategoryEntity createMainCategory(@RequestBody MainCategoryCreateRequest request) {
        return mainCategoryCreateService.createMainCategory(request);
    }
}
