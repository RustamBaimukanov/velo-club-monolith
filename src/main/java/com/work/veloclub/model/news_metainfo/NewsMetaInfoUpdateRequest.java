package com.work.veloclub.model.news_metainfo;

public record NewsMetaInfoUpdateRequest(
        Long id,
        String name,
        String file,
        Boolean isCore) {
}
