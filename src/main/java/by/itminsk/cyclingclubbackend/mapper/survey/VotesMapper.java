package by.itminsk.cyclingclubbackend.mapper.survey;

import by.itminsk.cyclingclubbackend.model.answer.Answer;
import by.itminsk.cyclingclubbackend.model.answer.dto.AnswerResponse;
import by.itminsk.cyclingclubbackend.model.user.User;
import by.itminsk.cyclingclubbackend.model.user.UserGetDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))

public interface VotesMapper {

    UserGetDto userToUserResponse(User user);

    Set<UserGetDto> userListToUserResponseList(Set<User> users);
}
