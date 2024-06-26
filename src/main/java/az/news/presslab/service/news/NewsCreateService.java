package az.news.presslab.service.news;

import az.news.presslab.entity.CategoryEntity;
import az.news.presslab.entity.NewsEntity;
import az.news.presslab.mapper.NewsMapper;
import az.news.presslab.repository.CategoryRepository;
import az.news.presslab.repository.NewsRepository;
import az.news.presslab.request.NewsCreateRequest;
import az.news.presslab.response.NewsReadResponse;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class NewsCreateService {
    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;
    private final NewsMapper newsMapper;

    public NewsCreateService(NewsRepository newsRepository, CategoryRepository categoryRepository, NewsMapper newsMapper) {
        this.newsRepository = newsRepository;
        this.categoryRepository = categoryRepository;
        this.newsMapper = newsMapper;
    }

    public NewsReadResponse createNews(NewsCreateRequest request) {
        CategoryEntity category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));

        NewsEntity news = newsMapper.toCreateEntity(request,category);
        news.setId(null);

        NewsEntity savedNews = newsRepository.save(news);
        return newsMapper.toReadResponse(savedNews);
    }

    public NewsReadResponse createNews(NewsCreateRequest request, String imagePath) {
        CategoryEntity category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));

        NewsEntity news = newsMapper.toCreateEntity(request, category);
        news.setId(null);
        news.setImagePath(imagePath);

        NewsEntity savedNews = newsRepository.save(news);
        return newsMapper.toReadResponse(savedNews);
    }
}