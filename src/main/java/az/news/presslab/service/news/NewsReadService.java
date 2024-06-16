package az.news.presslab.service.news;

import az.news.presslab.entity.NewsEntity;
import az.news.presslab.mapper.NewsMapper;
import az.news.presslab.repository.NewsRepository;
import az.news.presslab.response.NewsCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
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

    public Page<NewsReadResponse> getNewsPage(int page,int size){
       Pageable pageable = PageRequest.of(page,size);
       Page<NewsEntity> newsPage = newsRepository.findAll(pageable);

       List<NewsReadResponse> newsReadResponses = newsPage.stream()
               .map(newsMapper::toReadResponse)
               .toList();

       return new PageImpl<>(newsReadResponses,pageable,newsPage.getTotalElements());
    }

    public Page<NewsReadResponse> getNewsPageBySort(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page,size,Sort.by(sortBy));
        Page<NewsEntity> newsPage = newsRepository.findAll(pageable);

        List<NewsReadResponse> newsReadResponses = newsPage.stream()
                .map(newsMapper::toReadResponse)
                .toList();

        return new PageImpl<>(newsReadResponses,pageable,newsPage.getTotalElements());
    }

    public Page<NewsReadResponse> getNewsPageByFilter(Pageable pageable,
                                                      NewsCriteria newsCriteria){

        Specification<NewsEntity> spec = NewsSpecification.buildSpecification
                (newsCriteria.getTitle(),newsCriteria.getCategoryName(),newsCriteria.getContent());

        Page<NewsEntity> newsEntityPage = newsRepository.findAll(spec,pageable);

        List<NewsReadResponse> newsReadResponses = newsEntityPage.stream()
                .map(newsMapper::toReadResponse)
                .toList();

        return new PageImpl<>(newsReadResponses,pageable,newsEntityPage.getTotalElements());
    }

    public Page<NewsReadResponse> getNewsPageByFilterQuery(Pageable pageable,
                                                           NewsCriteria newsCriteria) {

        Page<NewsEntity> newsEntityPage = newsRepository.findByFilter
                (newsCriteria.getTitle(), newsCriteria.getCategoryName(), newsCriteria.getContent(), pageable);

        List<NewsReadResponse> newsReadResponses = newsEntityPage.getContent().stream()
                .map(newsMapper::toReadResponse)
                .toList();

        return new PageImpl<>(newsReadResponses, pageable, newsEntityPage.getTotalElements());
    }
}