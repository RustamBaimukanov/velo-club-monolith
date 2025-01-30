package com.work.veloclub.model.feedback;

public record FeedbackDto(Long id, String topic, String message, String name, String email) {
}
