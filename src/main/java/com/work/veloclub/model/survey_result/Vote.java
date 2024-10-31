//package com.work.veloclub.model.survey_result;
//
//import com.work.veloclub.model.answer.Answer;
//import com.work.veloclub.model.user.User;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@Table(name = "votes"
//        , uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "answer_id" }) }
//)
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class Vote {
//
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "answer_id")
//    private Answer answer;
//
//}
