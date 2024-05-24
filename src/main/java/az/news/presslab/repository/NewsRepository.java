package az.news.presslab.repository;

import az.news.presslab.entity.NewsEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity,Integer> {
    List<NewsEntity> findByCategoryId(int categoryId);

    Page<NewsEntity> findAll(Pageable pageable);
}
