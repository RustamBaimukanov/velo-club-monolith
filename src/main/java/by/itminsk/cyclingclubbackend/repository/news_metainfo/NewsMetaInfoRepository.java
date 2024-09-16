package by.itminsk.cyclingclubbackend.repository.news_metainfo;

import by.itminsk.cyclingclubbackend.model.news_metainfo.NewsMetaInfo;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Hidden
public interface NewsMetaInfoRepository extends JpaRepository<NewsMetaInfo, Long> {
}
