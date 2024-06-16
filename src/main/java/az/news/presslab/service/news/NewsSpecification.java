package az.news.presslab.service.news;

import az.news.presslab.entity.CategoryEntity;
import az.news.presslab.entity.NewsEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class NewsSpecification {
    public static Specification<NewsEntity> buildSpecification(String title, String categoryName, String content) {
        Specification<NewsEntity> spec = Specification.where(null);

        if (title != null && !title.isEmpty()) {
            spec = spec.and(hasTitle(title));
        }

        if (categoryName != null && !categoryName.isEmpty()) {
            spec = spec.and(hasCategoryName(categoryName));
        }

        if (content != null && !content.isEmpty()) {
            spec = spec.and(hasContent(content));
        }

        return spec;
    }

    public static Specification<NewsEntity> hasTitle(String title){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("title"),"%" +title + "%"));
    }


    public static Specification<NewsEntity> hasCategoryName(String categoryName) {
        return (root, query, criteriaBuilder) -> {
            Join<NewsEntity, CategoryEntity> categoryJoin = root.join("categoryId", JoinType.INNER);
            return criteriaBuilder.equal(categoryJoin.get("name"), categoryName);
        };
    }

    public static Specification<NewsEntity> hasContent(String content){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("content"),"%" + content + "%"));
    }
}
