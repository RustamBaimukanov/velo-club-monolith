package com.work.veloclub.controller;

import com.work.veloclub.mapper.news.NewsMapper;
import com.work.veloclub.model.news.NewsPostDTO;
import com.work.veloclub.service.news.NewsService;
import com.work.veloclub.util.exception_handler.error.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
            description = "API добавления новости.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь создал новость"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован – неверный токен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            }
    )
    @PostMapping
    public ResponseEntity<?> addNews(@RequestBody @Valid NewsPostDTO news) {

        return ResponseEntity.ok(NewsMapper.mapToNewsResponse(newsService.createNews(news)));
    }

    @Operation(
            summary = "Получение новости",
            description = "Получение новостей постранично",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь получил список новостей"),
                    @ApiResponse(responseCode = "500", description = "???")
            }
    )
    @GetMapping("/pages")
    public ResponseEntity<?> getNews(@RequestParam(defaultValue = "0", value = "page") int page,
                                     @RequestParam(defaultValue = "10", value = "size") int size) {
        return ResponseEntity.ok(NewsMapper.mapToNewsResponse(newsService.getNews(page, size)));
    }

    @Operation(
            summary = "Получение новости",
            description = "Получение новостей чанками(для мобильной версии)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь получил список новостей"),
                    @ApiResponse(responseCode = "500", description = "???")
            }
    )
    @GetMapping("/infinity")
    public ResponseEntity<?> getNews() {
        return ResponseEntity.ok(NewsMapper.mapToNewsResponse(newsService.getNews()));
    }

}

