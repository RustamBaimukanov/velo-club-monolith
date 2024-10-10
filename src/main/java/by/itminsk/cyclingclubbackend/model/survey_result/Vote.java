//package by.itminsk.cyclingclubbackend.model.survey_result;
//
//import by.itminsk.cyclingclubbackend.model.answer.Answer;
//import by.itminsk.cyclingclubbackend.model.user.User;
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
