package com.work.veloclub.controller.survey;

import com.work.veloclub.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/survey/answer")
@RequiredArgsConstructor
@Tag(name = "Опросы|Голосования")
@Slf4j
public class VoteController {

    private final UserService userService;


    @Operation(
            summary = "Проголосовать за выбранный ответ")
    @PostMapping("{id}")
    public ResponseEntity<?> addVote(@PathVariable Long id) {
        userService.voteFor(id);
        return ResponseEntity.ok("");
    }


    @Operation(
            summary = "Отменить свой голос(выдать ошибку если опрос закрылся)")
    @DeleteMapping("{id}")
    public ResponseEntity<?> removeVote(@PathVariable Long id) {
        userService.cancelVoteFor(id);
        return ResponseEntity.ok("");
    }
}
