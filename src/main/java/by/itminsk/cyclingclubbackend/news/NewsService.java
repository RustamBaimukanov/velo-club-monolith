package by.itminsk.cyclingclubbackend.news;

import by.itminsk.cyclingclubbackend.news.dto.NewsDTO;

import java.util.List;

public interface NewsService {

    NewsDTO getNews(Long id);

    List<NewsDTO> getNews();
}
