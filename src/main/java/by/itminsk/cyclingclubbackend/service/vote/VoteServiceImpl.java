//package by.itminsk.cyclingclubbackend.service.vote;
//
//import by.itminsk.cyclingclubbackend.model.answer.Answer;
//import by.itminsk.cyclingclubbackend.model.survey_result.Vote;
//import by.itminsk.cyclingclubbackend.model.user.User;
//import by.itminsk.cyclingclubbackend.repository.survey_result.SurveyResultRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class VoteServiceImpl implements VoteService{
//
//    private final SurveyResultRepository voteRepository;
//
//    @Override
//    public void create() {
//        voteRepository.save(new Vote(null, User.builder().id(1L).build(), Answer.builder().id(102L).build()));
//    }
//}
