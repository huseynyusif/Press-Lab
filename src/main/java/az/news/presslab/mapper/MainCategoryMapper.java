package az.news.presslab.mapper;

import az.news.presslab.entity.MainCategoryEntity;
import az.news.presslab.request.MainCategoryCreateRequest;
import org.mapstruct.Mapper;
import az.news.presslab.response.MainCategoryReadResponse;

@Mapper(componentModel = "spring")
public interface MainCategoryMapper {
    MainCategoryReadResponse toReadResponse(MainCategoryEntity mainCategoryEntity);

    MainCategoryEntity toCreateEntity(MainCategoryCreateRequest mainCategoryCreateRequest);
}
