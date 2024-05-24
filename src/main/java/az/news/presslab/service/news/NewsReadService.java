package az.news.presslab.service.news;

import az.news.presslab.entity.NewsEntity;
import az.news.presslab.mapper.NewsMapper;
import az.news.presslab.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import az.news.presslab.response.NewsReadResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsReadService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    @Autowired
    public NewsReadService(NewsRepository newsRepository, NewsMapper newsMapper) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
    }

    public List<NewsReadResponse> getAllNews() {
        return newsRepository.findAll().stream()
                .map(newsMapper::toReadResponse)
                .collect(Collectors.toList());
    }


    public NewsReadResponse getNewsById(int id) {

        return newsRepository.findById(id)
                .map(newsEntity ->{
                    newsEntity.setViews(newsEntity.getViews() + 1);
                    newsRepository.save(newsEntity);
                    return newsMapper.toReadResponse(newsEntity);
                        })
                .orElseThrow(() -> new RuntimeException("News not found with id " + id));
    }

    public List<NewsReadResponse> getNewsByCategoryId(int categoryId) {
        return newsRepository.findByCategoryId(categoryId).stream()
                .map(newsMapper::toReadResponse)
                .collect(Collectors.toList());
    }

    public Page<NewsEntity> getNewsPage(int page,int size){
        return newsRepository.findAll(PageRequest.of(page, size));
    }
}