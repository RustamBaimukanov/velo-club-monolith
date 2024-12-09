package com.work.veloclub.model.news;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record NewsDTO( Long id,

         String title,

         String content,

         LocalDate createdAt) {


}
