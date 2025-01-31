package com.work.veloclub.service.feedback;

import com.work.veloclub.model.feedback.Feedback;
import com.work.veloclub.model.feedback.FeedbackDto;

import java.util.List;

/**
 * Написать/получить отзывы.
 */
public interface FeedbackService {

    void createFeedback(FeedbackDto feedbackDto);
    Feedback getFeedback(Long id);

    List<Feedback> getFeedback();

}
