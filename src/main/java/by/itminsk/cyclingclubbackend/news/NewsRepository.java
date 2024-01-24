package by.itminsk.cyclingclubbackend.news;

import by.itminsk.cyclingclubbackend.news.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
}
