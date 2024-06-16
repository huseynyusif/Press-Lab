package az.news.presslab.controller.news;

import az.news.presslab.entity.NewsEntity;
import az.news.presslab.response.NewsCriteria;
import az.news.presslab.service.news.NewsReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
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

    @GetMapping("/page")
    public Page<NewsReadResponse> getNewsPage(
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10")int size){
        return newsReadService.getNewsPage(page,size);
    }

    @GetMapping("/page/sort")
    public Page<NewsReadResponse> getNewsPageBySort(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return newsReadService.getNewsPageBySort(page,size,sortBy);
    }

    @GetMapping("/page/filter")
    public Page<NewsReadResponse> getNewsPageByFilter(
            @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable,
            @ModelAttribute NewsCriteria newsCriteria ){
        return newsReadService.getNewsPageByFilter(pageable,newsCriteria);
    }

    @GetMapping("/page/filter/query")
    public Page<NewsReadResponse> getNewsPageByFilterQuery(
            @RequestParam @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable,
            @RequestParam(required = false) NewsCriteria newsCriteria )
 {
        return newsReadService.getNewsPageByFilterQuery(pageable,newsCriteria);
    }

}
