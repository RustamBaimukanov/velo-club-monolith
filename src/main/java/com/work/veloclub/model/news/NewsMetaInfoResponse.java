package com.work.veloclub.model.news;

public record NewsMetaInfoResponse(
        Long id,
        String name,
        byte[] file,
        String format) {
}
