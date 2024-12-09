package com.work.veloclub.model.answer;

import com.work.veloclub.model.question.Question;
import com.work.veloclub.model.user.User;
import com.work.veloclub.model.user_profile.UserProfile;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
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
    Set<UserProfile> users = new HashSet<>();


    public Answer addUser(UserProfile user) {
        users.add(user);
        user.getAnswers().add(this);
        return this;
    }


}
