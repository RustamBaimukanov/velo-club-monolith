package com.work.veloclub.model.news;

import com.work.veloclub.model.news_metainfo.NewsMetaInfoResponse;

import java.time.LocalDateTime;

public record NewsResponse(
        Long id,
        String title,
        String content,
        LocalDateTime createdDate,
        LocalDateTime updatedDate,
        NewsMetaInfoResponse files
) {
}
