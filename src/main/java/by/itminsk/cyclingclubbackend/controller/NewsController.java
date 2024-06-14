package by.itminsk.cyclingclubbackend.controller;

import by.itminsk.cyclingclubbackend.event.EventService;
import by.itminsk.cyclingclubbackend.event.dto.EventPostDto;
import by.itminsk.cyclingclubbackend.news.NewsService;
import by.itminsk.cyclingclubbackend.news.dto.NewsPostDTO;
import by.itminsk.cyclingclubbackend.team.TeamService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @PostMapping(value = "/news", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addNews(@ModelAttribute NewsPostDTO news) {
        newsService.createNews(news);
        return ResponseEntity.ok("");
    }

    @ResponseBody
    @PostMapping(value = "/news-test")
    public ResponseEntity<?> addNewsTest(@RequestBody NewsPostDTO news) {
        newsService.createNews(news);
        return ResponseEntity.ok("");
    }

    @GetMapping("/news")
    public ResponseEntity<?> getNews(@RequestParam(required = false) Long id) {
        if (id != null) {
            return ResponseEntity.ok(newsService.getNews(id));
        } else {
            return ResponseEntity.ok(newsService.getNews());
        }
    }

}

