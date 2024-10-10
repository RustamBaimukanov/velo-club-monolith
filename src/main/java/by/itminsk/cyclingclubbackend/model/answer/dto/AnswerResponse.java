package by.itminsk.cyclingclubbackend.model.answer.dto;

import by.itminsk.cyclingclubbackend.model.user.UserGetDto;

import java.util.Set;

public record AnswerResponse(Long id, Boolean ownOption, String answer, Set<UserGetDto> users) {
}
