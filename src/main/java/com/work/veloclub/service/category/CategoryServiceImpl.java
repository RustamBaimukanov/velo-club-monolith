package com.work.veloclub.service.category;

import com.work.veloclub.model.event.category.Category;
import com.work.veloclub.model.event.category.EventRaceType;
import com.work.veloclub.repository.event.CategoryRepository;
import com.work.veloclub.util.exception_handler.ObjectNotFound;
import com.work.veloclub.util.exception_handler.error_message.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAllByType(EventRaceType eventRaceType) {
        return categoryRepository.findAllByType(eventRaceType);
    }

    @Override
    public void categoryExistenceValidator(Long id) {
        categoryRepository.findById(id).orElseThrow(() -> new ObjectNotFound(ErrorMessages.CategoryErrors.NOT_FOUND));
    }
}
