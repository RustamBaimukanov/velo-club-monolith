package by.itminsk.cyclingclubbackend.controller.survey;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/survey/answer")
@RequiredArgsConstructor
@Tag(name = "Опросы|Голосования")
@Slf4j
public class VoteController {


    @Operation(
            summary = "Проголосовать за выбранный ответ")
    @PostMapping("{id}")
    public ResponseEntity<?> addVote(@PathVariable Long id) {
//        voteService.create();
        return ResponseEntity.ok("");
    }
}
