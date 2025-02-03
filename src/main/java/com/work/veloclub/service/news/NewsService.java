package com.work.veloclub.service.news;

import com.work.veloclub.model.news.News;
import com.work.veloclub.model.news.NewsDTO;
import com.work.veloclub.model.news.NewsPostDTO;
import com.work.veloclub.model.news.NewsUpdateRequest;
import jakarta.validation.Valid;

import java.util.List;

public interface NewsService {

    /**
     * Создает новость и возвращает результат пользователю
     *
     * @param newsPostDTO объект сохранения данных новости
     * @return созданная новость
     */
    News createNews(NewsPostDTO newsPostDTO);

    /**
     * Редактирует новость и возвращает результат пользователю
     *
     * @param newsUpdateRequest объект обновления данных новости
     * @return отредактированная новость
     */
    News updateNews(NewsUpdateRequest newsUpdateRequest);

    /**
     * Возвращает новость и список файлов(фото, документы)
     * @param id id новости
     * @return новость с полной информацией
     */
    NewsDTO getNews(Long id);

    /**
     * Возвращает список новостей с файлами помеченными как isCore = true(для картинки которая будет отображаться на фронте)
     * @return новости с порезанным контентом(для вместимости)
     */
    List<News> getNews();

    /**
     * Возвращает список новостей с файлами помеченными как isCore = true(для картинки которая будет отображаться на фронте) и разделенный на страницы
     * Если нужно отобразить последние 3 новости как в фигме для веба, использовать page=0 size=3
     * @return новости с порезанным контентом(для вместимости)
     */
    List<News> getNews(Integer page, Integer size);

    void validateCreateNewsContent(NewsPostDTO eventPostDto);
}
