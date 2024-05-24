package az.news.presslab.controller.category;

import az.news.presslab.service.category.CategoryReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import az.news.presslab.response.CategoryReadResponse;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryReadController {

    private final CategoryReadService categoryReadService;

    @GetMapping("/all")
    public List<CategoryReadResponse> getAllCategories(){
        return categoryReadService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryReadResponse getCategoryById(@PathVariable int id){
        return categoryReadService.getCategoryById(id);
    }
}
