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
    @PostMapping(value = "/news", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addNews(@ModelAttribute NewsPostDTO news) {
        newsService.createNews(news);
        return ResponseEntity.ok("");
    }

    @Operation(
            summary = "Добавление новости(для вызова без клиента)",
            description = "API добавления новости."
    )
    @ResponseBody
    @PostMapping(value = "/news-test")
    public ResponseEntity<?> addNewsTest(@RequestBody NewsPostDTO news) {
        newsService.createNews(news);
        return ResponseEntity.ok("");
    }

    @Operation(
            summary = "Получение новости",
            description = "API получения новости в одиночном варианте через id, либо все новости с учетом доступности для роли пользователя или конкретного пользователя отсортированном в порядке указанном в ТЗ(без пагинации, пока, следует обсудить)"
    )
    @GetMapping("/news")
    public ResponseEntity<?> getNews(@RequestParam(required = false) Long id) {
        if (id != null) {
            return ResponseEntity.ok(newsService.getNews(id));
        } else {
            return ResponseEntity.ok(newsService.getNews());
        }
    }

}
