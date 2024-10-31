package com.work.veloclub.repository.news;

import com.work.veloclub.model.news.News;
import com.work.veloclub.model.news.NewsDTO;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Hidden
public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("select new com.work.veloclub.model.news.NewsDTO(n.id, n.title, n.content, n.creationDate)" +
            " from News n left outer join n.availableRoles r left outer join n.availableUsers u where r.id = :roleId or u.id = :userId order by n.creationDate desc ")
    List<NewsDTO> findAllByRoleOrUser(Long roleId, Long userId, Pageable pageable);

}
