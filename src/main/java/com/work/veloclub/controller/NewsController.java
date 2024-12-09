package com.work.veloclub.controller;

import com.work.veloclub.mapper.news.NewsMapper;
import com.work.veloclub.model.news.NewsPostDTO;
import com.work.veloclub.service.news.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/news")
@RequiredArgsConstructor
@Tag(name = "Новости", description = "Операции связанные с новостями")
public class NewsController {

    private final NewsService newsService;

    @Operation(
            summary = "Добавление новости",
            description = "API добавления новости."
    )
    @PostMapping
    public ResponseEntity<?> addNews(@RequestBody @Valid NewsPostDTO news) {

        return ResponseEntity.ok(NewsMapper.mapToNewsResponse(newsService.createNews(news)));
    }

    @Operation(
            summary = "Получение новости",
            description = "API получения новости в одиночном варианте через id, либо все новости с учетом доступности для роли пользователя или конкретного пользователя отсортированном в порядке указанном в ТЗ(без пагинации, пока, следует обсудить)"
    )
    @GetMapping
    public ResponseEntity<?> getNews(@RequestParam(defaultValue = "0", value = "page") int page,
                                     @RequestParam(defaultValue = "10", value = "size") int size) {
        return ResponseEntity.ok(NewsMapper.mapToNewsResponse(newsService.getNews(page, size)));
    }

}

