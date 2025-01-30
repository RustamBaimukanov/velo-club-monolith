package com.work.veloclub.service.feedback;

import com.work.veloclub.model.feedback.Feedback;
import com.work.veloclub.model.feedback.FeedbackDto;
import com.work.veloclub.repository.feedback.FeedbackRepository;
import com.work.veloclub.util.exception_handler.ObjectNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Override
    @Transactional
    public void createFeedback(FeedbackDto feedbackDto) {
        Feedback feedback = new Feedback();
        feedback.setTopic(feedbackDto.topic());
        feedback.setMessage(feedbackDto.message());
        feedback.setName(feedbackDto.name());
        feedback.setEmail(feedbackDto.email());
    }

    @Override
    public Feedback getFeedback(Long id) {
        return feedbackRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Отзыв не найден."));
    }

    @Override
    public List<Feedback> getFeedback() {
        return feedbackRepository.findAll();
    }
}
