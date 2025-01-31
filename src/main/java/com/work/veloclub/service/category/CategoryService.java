package com.work.veloclub.service.category;

import com.work.veloclub.model.event.category.Category;
import com.work.veloclub.model.event.category.EventRaceType;

import java.util.List;

/**
 * Что это за категории?
 * Различные форматы заездов велосипедистов
 * Список можно посмотреть в 34 странице 1 части ТЗ
 */
public interface CategoryService {

    /**
     *
     * @return возвращает список категории независимо от типа
     */
    List<Category> findAll();

    /**
     *
     * @param eventRaceType - тип категории
     * @return возвращает список категории по типу
     */
    List<Category> findAllByType(EventRaceType eventRaceType);
}
