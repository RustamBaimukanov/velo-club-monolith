package com.work.veloclub.model.user;

import java.time.LocalDate;


public record RegisterDto(String firstName,
                          String lastName,
                          String phoneNumber,
                          String email,
                          String password,
                          String confirmPassword,
                          Boolean termsAccepted,
                          LocalDate birthDate,
                          GenderEnum gender,
                          Long city) {


}
