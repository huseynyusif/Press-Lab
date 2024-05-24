package az.news.presslab.service.maincategory;

import az.news.presslab.mapper.MainCategoryMapper;
import az.news.presslab.repository.MainCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import az.news.presslab.response.MainCategoryReadResponse;

import java.util.List;

@Service
public class MainCategoryReadService {

    private final MainCategoryRepository mainCategoryRepository;
    private final MainCategoryMapper mainCategoryMapper;

    @Autowired
    public MainCategoryReadService(MainCategoryRepository mainCategoryRepository, MainCategoryMapper mainCategoryMapper) {
        this.mainCategoryRepository = mainCategoryRepository;
        this.mainCategoryMapper = mainCategoryMapper;
    }

    public List<MainCategoryReadResponse> getAllMainCategories() {

        return mainCategoryRepository.findAll().stream()
                .map(mainCategoryMapper::toReadResponse).toList();
    }

    public MainCategoryReadResponse getMainCategoryById(int id) {

        return mainCategoryRepository.findById(id)
                .map(mainCategoryMapper::toReadResponse).orElseThrow(() -> new RuntimeException("Main category not found with id " + id));
    }
}