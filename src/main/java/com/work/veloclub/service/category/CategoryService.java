package com.work.veloclub.service.category;

import com.work.veloclub.model.event.category.Category;
import com.work.veloclub.model.event.category.EventRaceType;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    List<Category> findAllByType(EventRaceType eventRaceType);
}
