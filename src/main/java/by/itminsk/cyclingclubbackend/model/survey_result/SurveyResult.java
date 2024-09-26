package by.itminsk.cyclingclubbackend.model.survey_result;

import by.itminsk.cyclingclubbackend.model.answer.Answer;
import by.itminsk.cyclingclubbackend.model.survey.VisibilityLevelEnum;
import by.itminsk.cyclingclubbackend.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "survey_results", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "answer_id" }) })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResult {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @Enumerated(EnumType.STRING)
    @Column(name = "visibility_level")
    private VisibilityLevelEnum visibilityLevel;
}
