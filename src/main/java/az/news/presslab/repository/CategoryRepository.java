package az.news.presslab.repository;

import az.news.presslab.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {
    List<CategoryEntity> findByMainCategoryId(int mainCategoryId);
}
