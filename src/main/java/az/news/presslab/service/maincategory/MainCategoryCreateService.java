package az.news.presslab.service.maincategory;

import az.news.presslab.entity.MainCategoryEntity;
import az.news.presslab.mapper.MainCategoryMapper;
import az.news.presslab.repository.MainCategoryRepository;
import az.news.presslab.request.MainCategoryCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainCategoryCreateService {
    private final MainCategoryRepository mainCategoryRepository;
    private final MainCategoryMapper mainCategoryMapper;


    public MainCategoryEntity createMainCategory(MainCategoryCreateRequest request) {
        MainCategoryEntity mainCategoryEntity = mainCategoryMapper.toCreateEntity(request);
        return mainCategoryRepository.save(mainCategoryEntity);
    }
}