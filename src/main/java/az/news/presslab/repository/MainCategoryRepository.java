package az.news.presslab.repository;

import az.news.presslab.entity.MainCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainCategoryRepository extends JpaRepository<MainCategoryEntity,Integer> {
}
