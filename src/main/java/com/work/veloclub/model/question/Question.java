package com.work.veloclub.model.question;

import com.work.veloclub.model.answer.Answer;
import com.work.veloclub.model.survey.Survey;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "question")
    private String question;

    @NotNull
    @Column(name = "allow_multiple_answer")
    private Boolean allowMultipleAnswer;

    /***
     * Вопрос, ответ на который от пользователя обязателен
     */
    @NotNull
    @Column(name = "is_required")
    private Boolean isRequired;

    @Builder.Default
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    public Question addAnswer(Answer answer) {
        answers.add(answer);
        answer.setQuestion(this);
        return this;
    }
}

