package az.news.presslab.mapper;

import az.news.presslab.entity.CategoryEntity;
import az.news.presslab.entity.MainCategoryEntity;
import az.news.presslab.request.CategoryCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import az.news.presslab.response.CategoryReadResponse;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
   CategoryReadResponse toReadResponse (CategoryEntity categoryEntity);


    @Mapping(source = "categoryCreateRequest.name", target = "name")
    @Mapping(source = "mainCategoryEntity.id", target = "mainCategoryId")
   CategoryEntity toCategoryEntity(CategoryCreateRequest categoryCreateRequest, MainCategoryEntity mainCategoryEntity);
}
