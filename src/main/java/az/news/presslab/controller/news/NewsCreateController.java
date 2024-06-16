package az.news.presslab.controller.news;

import az.news.presslab.controller.FileUploadController;
import az.news.presslab.request.NewsCreateRequest;
import az.news.presslab.response.NewsReadResponse;
import az.news.presslab.service.news.NewsCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/news")
public class NewsCreateController {
    private final NewsCreateService newsCreateService;
    private final FileUploadController fileUploadController;

    @Autowired
    public NewsCreateController(NewsCreateService newsCreateService, FileUploadController fileUploadController) {
        this.newsCreateService = newsCreateService;
        this.fileUploadController = fileUploadController;
    }

    @PostMapping("/create")
    public NewsReadResponse createNews(@RequestBody NewsCreateRequest request) {
        return newsCreateService.createNews(request);
    }

    @PostMapping("/create/add")
    public NewsReadResponse createNews(@RequestPart NewsCreateRequest request,
                                 @RequestPart MultipartFile file) {
        String imagePath = fileUploadController.uploadFile(file);
        //NewsCreateRequest request = new NewsCreateRequest(title, content, author, categoryId);
        return newsCreateService.createNews(request, imagePath);
    }
}
