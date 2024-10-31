package com.work.veloclub.model.survey;

import com.work.veloclub.model.question.Question;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "surveys")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Survey {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "allow_change_answer", nullable = false)
    private Boolean allowChangeAnswer;

    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    /***
     * Сокрытие результатов(публичный/непубличный)
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "result_visibility", nullable = false)
    private SurveyResultVisibilityEnum resultVisibility;

    /***
     * Статус опроса(активный/неактивный)
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SurveyStatusEnum surveyStatus;

    /***
     * Видимость голосующих пользователей(анонимные/известные)
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "user_visibility", nullable = false)
    private SurveyUserVisibilityEnum userVisibility;

    @Builder.Default
    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
    }

    public Survey addQuestion(Question question) {
        questions.add(question);
        question.setSurvey(this);
        return this;
    }

}
