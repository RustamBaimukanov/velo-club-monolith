package com.work.veloclub.repository.event;

import com.work.veloclub.model.event.category.Category;
import com.work.veloclub.model.event.category.EventRaceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByType(EventRaceType eventRaceType);
}
