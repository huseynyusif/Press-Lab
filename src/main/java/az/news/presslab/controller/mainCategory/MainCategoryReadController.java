package az.news.presslab.controller.mainCategory;

import az.news.presslab.service.maincategory.MainCategoryReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import az.news.presslab.response.MainCategoryReadResponse;

import java.util.List;

@RestController
@RequestMapping("/main-categories")
public class MainCategoryReadController {

    private final MainCategoryReadService mainCategoryReadService;

    @Autowired
    public MainCategoryReadController(MainCategoryReadService mainCategoryReadService) {
        this.mainCategoryReadService = mainCategoryReadService;
    }

    @GetMapping
    public List<MainCategoryReadResponse> getAllMainCategories() {
        return mainCategoryReadService.getAllMainCategories();
    }

    @GetMapping("/{id}")
    public MainCategoryReadResponse getMainCategoryById(@PathVariable int id) {
        return mainCategoryReadService.getMainCategoryById(id);
    }
}