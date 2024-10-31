package com.work.veloclub.mapper.survey;

import com.work.veloclub.model.answer.Answer;
import com.work.veloclub.model.answer.dto.AnswerResponse;
import com.work.veloclub.model.answer.dto.CreateAnswerRequest;
import com.work.veloclub.model.answer.dto.UpdateAnswerRequest;
import com.work.veloclub.model.user.User;
import com.work.veloclub.model.user.UserGetDto;

import java.util.Set;
import java.util.stream.Collectors;

public class AnswerMapper {

    public static Answer mapToAnswer(CreateAnswerRequest request) {
        if (request == null) {
            return null;
        }

        Answer answer = new Answer();
        answer.setOwnOption(request.ownOption());
        answer.setAnswer(request.answer());
        return answer;
    }

    public static Answer mapToAnswer(UpdateAnswerRequest request) {
        if (request == null) {
            return null;
        }

        Answer answer = new Answer();
        answer.setId(request.id()); // Получаем id для обновления
        answer.setOwnOption(request.ownOption());
        answer.setAnswer(request.answer());
        return answer;
    }

    public static AnswerResponse answerToAnswerResponse(Answer answer) {
        Set<UserGetDto> userDtos = answer.getUsers().stream()
                .map(AnswerMapper::userToUserGetDto)
                .collect(Collectors.toSet());

        return new AnswerResponse(
                answer.getId(),
                answer.getOwnOption(),
                answer.getAnswer(),
                userDtos
        );
    }

    public static UserGetDto userToUserGetDto(User user) {
        return new UserGetDto(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        );
    }
}
