package az.news.presslab.controller.news;

import az.news.presslab.entity.NewsEntity;
import az.news.presslab.service.news.NewsReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import az.news.presslab.response.NewsReadResponse;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<NewsReadResponse> getNewsById(@PathVariable int id) {
        Optional<NewsReadResponse> news = Optional.ofNullable(newsReadService.getNewsById(id));
        return news.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404).body(null));
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
