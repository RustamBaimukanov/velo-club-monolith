package com.work.veloclub.repository.news_metainfo;

import com.work.veloclub.model.news_metainfo.NewsMetaInfo;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsMetaInfoRepository extends JpaRepository<NewsMetaInfo, Long> {
}
