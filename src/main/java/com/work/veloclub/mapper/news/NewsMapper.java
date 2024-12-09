package com.work.veloclub.mapper.news;

import com.work.veloclub.model.news.News;
import com.work.veloclub.model.news.NewsDTO;
import com.work.veloclub.model.news.NewsMetaInfoResponse;
import com.work.veloclub.model.news.NewsResponse;
import com.work.veloclub.model.news_metainfo.NewsMetaInfo;
import com.work.veloclub.util.exception_handler.UnacceptableDataException;

import java.util.List;

public class NewsMapper {

    public static NewsResponse mapToNewsResponse(News news) {
        NewsMetaInfoResponse metaInfo = null;
        if (!news.getMetaInfo().isEmpty()) {
            metaInfo = news.getMetaInfo()
                    .stream()
                    .filter(NewsMetaInfo::getIsCore)
                    .findFirst()
                    .map(meta -> new NewsMetaInfoResponse(meta.getId(), meta.getName(), meta.getMetaInfo(), meta.getFormat()))
                    .orElse(null);
        }
        return new NewsResponse(
                news.getId(),
                news.getTitle(),
                news.getContent(),
                news.getCreatedAt(),
                metaInfo);
    }

    public static List<NewsResponse> mapToNewsResponse(List<News> newsList){
        return newsList.stream().map(NewsMapper::mapToNewsResponse).toList();
    }
}
