package com.work.veloclub.model.news_metainfo;

import jakarta.persistence.Column;


public record NewsMetaInfoCreateRequest(
        String name,
        String file,
        Boolean isCore) {
}
