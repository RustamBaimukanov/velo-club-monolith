package by.itminsk.cyclingclubbackend.model.survey;

import by.itminsk.cyclingclubbackend.model.question.Question;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.repository.EntityGraph;

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
//@NamedEntityGraph(
//        name = "graph.Survey.questions",
//        attributeNodes = {
//                @NamedAttributeNode(value = "questions"
//                        ,
//                        subgraph = "graph.Question.answers"
//                )
//        }
//        ,
//        subgraphs = {
//                @NamedSubgraph(
//                        name = "graph.Question.answers",
//                        attributeNodes = {
//                                @NamedAttributeNode(value = "answers"
////                                        , subgraph = "graph.Answer.votes"
//                                )
//                        }
//                        )
////                ,
////                @NamedSubgraph(
////                        name = "graph.Answer.votes",
////                        attributeNodes = {
////                                @NamedAttributeNode(value = "votes")
////                        }
////                )
//        }
//)
public class Survey {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Size(max = 50)
    @NotBlank
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "allow_change_answer")
    private Boolean allowChangeAnswer;

    @NotNull
    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    /***
     * Сокрытие результатов(публичный/непубличный)
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "result_visibility")
    private SurveyResultVisibilityEnum resultVisibility;

    /***
     * Статус опроса(активный/неактивный)
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SurveyStatusEnum surveyStatus;

    /***
     * Видимость голосующих пользователей(анонимные/известные)
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "user_visibility")
    private SurveyUserVisibilityEnum userVisibility;

    @Builder.Default
    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    public Survey addQuestion(Question question) {
        questions.add(question);
        question.setSurvey(this);
        return this;
    }

}
