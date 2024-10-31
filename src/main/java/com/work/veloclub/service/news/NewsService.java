package com.work.veloclub.service.news;

import com.work.veloclub.model.news.NewsDTO;
import com.work.veloclub.model.news.NewsPostDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface NewsService {

    void createNews(@Valid NewsPostDTO newsPostDTO);


    NewsDTO getNews(Long id);

    List<NewsDTO> getNews(Integer page);

    void validateCreateNewsContent(NewsPostDTO eventPostDto);

    List<NewsDTO> getLatestNews();
}
