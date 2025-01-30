package com.work.veloclub.model.team;

import com.work.veloclub.model.user.UserGetDto;

import java.util.List;

public record TeamWithUsersDTO(Long id, String name, byte[] photo, String format, List<UserGetDto> users) {
}
