package com.work.veloclub.controller;

import com.work.veloclub.model.event.category.EventRaceType;
import com.work.veloclub.service.category.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/categories")
@RequiredArgsConstructor
@Tag(name = "Мероприятия|Категории", description = "Операции связанные с категориями мероприятия")
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;


    @Operation(
            summary = "Получение списка категории мероприятия",
            description = "Получение списка категории мероприятия по типу или сразу все(эти категории константны, в базе содержатся в виде справочника)"
    )
    @GetMapping
    public ResponseEntity<?> getCategory(@RequestParam(required = false) EventRaceType type) {
        if (type == null) {
            return ResponseEntity.ok(categoryService.findAll());
        } else return ResponseEntity.ok(categoryService.findAllByType(type));
    }
}
