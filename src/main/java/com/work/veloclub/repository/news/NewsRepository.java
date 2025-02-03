package com.work.veloclub.repository.news;

import com.work.veloclub.model.news.News;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    @EntityGraph(attributePaths = {"metaInfo"})
    @Query("select n from News n where n.id = ?1")
    Optional<News> findByIdWithMetaInfo(Long id);

    @EntityGraph(attributePaths = {"metaInfo"})
    @Query("select n from News n")
    Page<News> findAllWithMetaInfo(Pageable pageable);

    @EntityGraph(attributePaths = {"metaInfo"})
    @Query("select n from News n")
    List<News> findAllWithMetaInfo(Sort sort);

}
