package com.work.veloclub.model.news;

import com.work.veloclub.model.news_metainfo.NewsMetaInfoCreateRequest;
import com.work.veloclub.model.news_metainfo.NewsMetaInfoUpdateRequest;
import com.work.veloclub.model.role.RolesEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record NewsUpdateRequest(@NotBlank(message = "Название новости не может быть пустым")
                                @Size(max = 255, message = "Название новости не может превышать 255 символов")
                                String title,
                                @Size(max = 4000, message = "Описание новости не может превышать N символов")
                                String content,
                                @NotNull(message = "Не выбрана роль получателей")
                                RolesEnum recipientCategory,
                                List<Long> recipients,

                                List<NewsMetaInfoUpdateRequest> newsMetaInfoUpdateRequests) {
}
