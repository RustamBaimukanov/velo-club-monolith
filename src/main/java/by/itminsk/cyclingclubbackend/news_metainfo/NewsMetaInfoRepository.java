package by.itminsk.cyclingclubbackend.news_metainfo;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Hidden
public interface NewsMetaInfoRepository extends JpaRepository<NewsMetaInfo, Long> {
}
