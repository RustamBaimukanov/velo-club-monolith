package by.itminsk.cyclingclubbackend.model.survey;

import by.itminsk.cyclingclubbackend.model.event_result.EventResult;
import by.itminsk.cyclingclubbackend.model.question.Question;
import by.itminsk.cyclingclubbackend.model.user.GenderEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "surveys")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Survey {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "allow_multiple_answer")
    private Boolean allowMultipleAnswer = false;

    @Column(name = "allow_change_answer")
    private Boolean allowChangeAnswer = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "visibility_level")
    private VisibilityLevelEnum visibilityLevel;

    @OneToMany
    @JoinColumn(name = "survey_id")
    private List<Question> questions = new ArrayList<>();

}
