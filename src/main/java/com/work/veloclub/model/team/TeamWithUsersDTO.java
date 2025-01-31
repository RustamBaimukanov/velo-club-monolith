package com.work.veloclub.model.team;

import com.work.veloclub.model.user.UserGetDto;

import java.util.List;

public record TeamWithUsersDTO(Long id, String name,String photo, List<UserGetDto> users) {
}
