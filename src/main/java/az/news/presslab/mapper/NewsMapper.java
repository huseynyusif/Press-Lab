package az.news.presslab.mapper;

import az.news.presslab.entity.CategoryEntity;
import az.news.presslab.entity.NewsEntity;
import az.news.presslab.request.NewsCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import response.NewsReadResponse;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    NewsReadResponse toReadResponse(NewsEntity newsEntity);

    @Mapping(source = "categoryEntity.id", target = "categoryId")
    NewsEntity toCreateEntity(NewsCreateRequest newsCreateRequest, CategoryEntity categoryEntity);
}
