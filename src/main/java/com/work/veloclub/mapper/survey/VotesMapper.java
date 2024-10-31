package com.work.veloclub.mapper.survey;

import com.work.veloclub.model.user.User;
import com.work.veloclub.model.user.UserGetDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))

public interface VotesMapper {

    UserGetDto userToUserResponse(User user);

    Set<UserGetDto> userListToUserResponseList(Set<User> users);
}
