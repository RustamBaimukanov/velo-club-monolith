package by.itminsk.cyclingclubbackend.model.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


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
