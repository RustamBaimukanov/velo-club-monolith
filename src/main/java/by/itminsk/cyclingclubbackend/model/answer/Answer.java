package by.itminsk.cyclingclubbackend.model.answer;

import by.itminsk.cyclingclubbackend.model.question.Question;
import by.itminsk.cyclingclubbackend.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "answers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(name = "own_option")
    private Boolean ownOption;

    @NotBlank
    @Size(max = 50)
    @Column(name = "answer")
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToMany
    @JoinTable(
            name = "votes",
            joinColumns = @JoinColumn(name = "answer_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    Set<User> users = new HashSet<>();


    public Answer addUser(User user) {
        users.add(user);
        user.getAnswers().add(this);
        return this;
    }

//    public Answer addVote(Vote vote) {
//        votes.add(vote);
//        vote.setAnswer(this);
//        return this;
//    }

}
