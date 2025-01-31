package com.work.veloclub.model.user;

public record UserGetDto(
        Long id,

        String email,

        String firstName,

        String lastName,

        String surname,

        String avatar,

        String format
) {
}
