package by.itminsk.cyclingclubbackend.controller;

import by.itminsk.cyclingclubbackend.event.EventService;
import by.itminsk.cyclingclubbackend.event.dto.EventPostDto;
import by.itminsk.cyclingclubbackend.news.NewsService;
import by.itminsk.cyclingclubbackend.news.dto.NewsPostDTO;
import by.itminsk.cyclingclubbackend.team.TeamService;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@Tag(name = "Новости", description = "Операции связанные с новостями")
public class NewsController {

    private final NewsService newsService;

    @Operation(
            summary = "Добавление новости",
            description = "API добавления новости."
    )
    @PostMapping(value = "/news", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNews(@RequestBody NewsPostDTO news) {
        newsService.createNews(news);
        return ResponseEntity.ok("");
    }

    @Operation(
            summary = "Получение новости",
            description = "API получения новости в одиночном варианте через id, либо все новости с учетом доступности для роли пользователя или конкретного пользователя отсортированном в порядке указанном в ТЗ(без пагинации, пока, следует обсудить)"
    )
    @GetMapping("/news")
    public ResponseEntity<?> getNews(@RequestParam(required = false) Long id, @RequestParam(required = false, defaultValue = "0") Integer page) {
        if (id != null) {
            return ResponseEntity.ok(newsService.getNews(id));
        } else {
            return ResponseEntity.ok(newsService.getNews(page));
        }
    }

    @Operation(
            summary = "Получение последних новостей",
            description = "Получение последних новостей(3 последние по дате)"
    )
    @GetMapping("/news/latest")
    public ResponseEntity<?> getLatestNews() {
        return ResponseEntity.ok(newsService.getLatestNews());
    }

}

