package by.itminsk.cyclingclubbackend.news;

import by.itminsk.cyclingclubbackend.event.dto.EventPostDto;
import by.itminsk.cyclingclubbackend.news.dto.NewsDTO;
import by.itminsk.cyclingclubbackend.news.dto.NewsPostDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface NewsService {

    void createNews(@Valid NewsPostDTO newsPostDTO);


    NewsDTO getNews(Long id);

    List<NewsDTO> getNews();

    void validateCreateNewsContent(NewsPostDTO eventPostDto);

}
