package com.work.veloclub.service.news;

import com.work.veloclub.model.news.News;
import com.work.veloclub.model.news.NewsDTO;
import com.work.veloclub.model.news.NewsPostDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface NewsService {

    News createNews(NewsPostDTO newsPostDTO);


    NewsDTO getNews(Long id);

    List<NewsDTO> getNews(Integer page);

    List<News> getNews(Integer page, Integer size);

    void validateCreateNewsContent(NewsPostDTO eventPostDto);
}
