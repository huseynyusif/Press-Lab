package az.news.presslab.controller.news;

import az.news.presslab.entity.NewsEntity;
import az.news.presslab.service.news.NewsReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import response.NewsReadResponse;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsReadController {

    private final NewsReadService newsReadService;

    @Autowired
    public NewsReadController(NewsReadService newsReadService) {
        this.newsReadService = newsReadService;
    }

    @GetMapping
    public List<NewsReadResponse> getAllNews() {
        return newsReadService.getAllNews();
    }

    @GetMapping("/{id}")
    public NewsReadResponse getNewsById(@PathVariable int id) {
        return newsReadService.getNewsById(id);
    }

    @GetMapping("/category/{categoryId}")
    public List<NewsReadResponse> getNewsByCategoryId(@PathVariable int categoryId) {
        return newsReadService.getNewsByCategoryId(categoryId);
    }

    @GetMapping("/news")
    public Page<NewsEntity> getNewsPage(
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10")int size){
        return newsReadService.getNewsPage(page,size);
    }

}
