package by.itminsk.cyclingclubbackend.model.user;

public record UserGetDto(
        Long id,

        String email,

        String firstName,

        String lastName
) { }
