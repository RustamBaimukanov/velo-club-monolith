package by.itminsk.cyclingclubbackend.news;

import by.itminsk.cyclingclubbackend.news.News;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@Hidden
public interface NewsRepository extends JpaRepository<News, Long> {
}
