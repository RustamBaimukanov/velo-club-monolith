package com.work.veloclub.model.news;

import com.work.veloclub.model.news_metainfo.NewsMetaInfoResponse;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record NewsDTO(Long id,

                      String title,

                      String content,

                      LocalDateTime createdDate,

                      LocalDateTime updatedDate,

                      List<NewsMetaInfoResponse> metaInfoResponses) {


}
