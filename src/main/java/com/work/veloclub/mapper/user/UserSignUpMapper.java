package com.work.veloclub.mapper.user;

import com.work.veloclub.model.city.City;
import com.work.veloclub.model.role.Role;
import com.work.veloclub.model.role.RoleEnum;
import com.work.veloclub.model.team.Team;
import com.work.veloclub.model.user.RegisterByAdminDto;
import com.work.veloclub.model.user.RegisterDto;
import com.work.veloclub.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.io.IOException;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserSignUpMapper {

    default User userDtoToUser(RegisterByAdminDto userDto) throws IOException {
        //TODO странное решение с ролью
        User user = new User();
        user.setPhoneNumber(userDto.tel());
        user.setEmail(userDto.email());
        user.setFirstName(userDto.firstName());
        user.setLastName(userDto.lastName());
        user.setBirthDate(userDto.birth());
        user.setSex(userDto.gender());
        user.setHeight(userDto.height());
        user.setWeight(userDto.weight());
        user.setTeam(Team.builder().id(userDto.club()).build());
        user.setCity(City.builder().id(userDto.region()).build());
        switch (userDto.qualification()) {
            case ADMIN -> user.setRole(Role.builder().id(1L).build());
            case SPORTSMAN -> user.setRole(Role.builder().id(2L).build());
            case DABBLER -> user.setRole(Role.builder().id(3L).build());
        }
        if (userDto.userImg() != null) {
            user.setPhoto(userDto.userImg().getBytes());
            user.setPhotoFormat(userDto.userImg().getContentType());
        } else {
            user.setPhoto(null);
            user.setPhotoFormat(null);
        }
        return user;

    }


    @Mapping(target = "city.id", source = "city")
    @Mapping(target = "sex", source = "gender")
    @Mapping(target = "role.id", constant = "3L")
    User signUpDtoToUser(RegisterDto userDto);


    @Named("QualificationToRoleId")
    default Long qualificationToRoleId(RoleEnum roleEnum) {
        return switch (roleEnum) {
            case ADMIN -> 1L;
            case SPORTSMAN -> 2L;
            case DABBLER -> 3L;

        };
    }
}
