//package com.work.veloclub.service.vote;
//
//import com.work.veloclub.model.answer.Answer;
//import com.work.veloclub.model.survey_result.Vote;
//import com.work.veloclub.model.user.User;
//import com.work.veloclub.repository.survey_result.SurveyResultRepository;
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
