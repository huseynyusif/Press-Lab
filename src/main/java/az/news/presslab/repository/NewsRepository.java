package az.news.presslab.repository;

import az.news.presslab.entity.NewsEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity,Integer> , JpaSpecificationExecutor<NewsEntity>, PagingAndSortingRepository<NewsEntity,Integer> {
    List<NewsEntity> findByCategoryId(int categoryId);


    @Query("SELECT n FROM NewsEntity n JOIN CategoryEntity c ON n.categoryId = c.id WHERE (:title IS NULL OR n.title ILIKE %:title%) AND (:content IS NULL OR n.content ILIKE %:content%) AND (:category IS NULL OR c.name = :category)")
    Page<NewsEntity> findByFilter(@Param("title") String title, @Param("content") String content, @Param("category") String category, Pageable pageable);

}
