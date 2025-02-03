package com.work.veloclub.model.news_metainfo;

public record NewsMetaInfoResponse(
        Long id,
        String name,
        String file,

        Boolean isCore) {
}
