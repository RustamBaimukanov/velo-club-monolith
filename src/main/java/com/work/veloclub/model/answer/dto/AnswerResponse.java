package com.work.veloclub.model.answer.dto;

import com.work.veloclub.model.user.UserGetDto;

import java.util.Set;

public record AnswerResponse(Long id, Boolean ownOption, String answer, Set<UserGetDto> users) {
}
