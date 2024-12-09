package com.work.veloclub.model.news;

import com.work.veloclub.model.news_metainfo.NewsMetaInfo;

import java.time.LocalDate;

public record NewsResponse(
        Long id,
        String title,
        String content,
        LocalDate createdAt,

        NewsMetaInfoResponse files
) {
}
