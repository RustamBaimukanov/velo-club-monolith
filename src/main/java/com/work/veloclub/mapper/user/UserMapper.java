package com.work.veloclub.mapper.user;


import com.work.veloclub.mapper.city.CityMapper;
import com.work.veloclub.mapper.role.RoleMapper;
import com.work.veloclub.mapper.social_network.SocialNetworkMapper;
import com.work.veloclub.mapper.team.TeamMapper;
import com.work.veloclub.mapper.trophy.TrophyMapper;
import com.work.veloclub.model.city.City;
import com.work.veloclub.model.team.Team;
import com.work.veloclub.model.user.*;
import com.work.veloclub.model.user_profile.UserProfile;

import java.util.Base64;

public class UserMapper {

    public static User mapToUser(RegisterDto registerDto) {
        if (registerDto == null) {
            return null;
        }
        User user = new User();
        user.setPhoneNumber(registerDto.phoneNumber());
        user.setPassword(registerDto.password());
        user.setStatus(UserStatusEnum.ACTIVE);
        return user;
    }

    public static User mapToUser(RegisterByAdminDto registerDto) {
        if (registerDto == null) {
            return null;
        }
        User user = new User();
        user.setPhoneNumber(registerDto.phoneNumber());
        user.setPassword(registerDto.password());
        user.setStatus(UserStatusEnum.ACTIVE);

        return user;
    }

    public static UserProfile mapToUserProfile(RegisterDto registerDto) {
        if (registerDto == null) {
            return null;
        }
        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName(registerDto.firstName());
        userProfile.setLastName(registerDto.lastName());
        userProfile.setSurname(registerDto.surname());
        userProfile.setEmail(registerDto.email());
        userProfile.setBirthDate(registerDto.birthDate());
        userProfile.setGender(registerDto.gender());
        City city = new City();
        city.setId(registerDto.city());
        userProfile.setCity(city);
        return userProfile;
    }

    public static UserProfile mapToUserProfile(RegisterByAdminDto registerDto) {
        if (registerDto == null) {
            return null;
        }
        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName(registerDto.firstName());
        userProfile.setLastName(registerDto.lastName());
        userProfile.setSurname(registerDto.surname());
        userProfile.setEmail(registerDto.email());
        userProfile.setBirthDate(registerDto.birthDate());
        userProfile.setGender(registerDto.gender());
        userProfile.setHeight(registerDto.height());
        userProfile.setWeight(registerDto.weight());
        userProfile.setPhoto(registerDto.avatar() != null ? Base64.getDecoder().decode(registerDto.avatar()) : null);
        if (registerDto.team() != null) userProfile.setTeam(Team.builder().id(registerDto.team()).build());
        City city = new City();
        city.setId(registerDto.city());
        userProfile.setCity(city);
        return userProfile;
    }

    public static UserInfoDTO mapToUserInfo(User user) {
        if (user == null) {
            return null;
        }
        return new UserInfoDTO(user.getId(),
                user.getUserProfile().getEmail(),
                user.getPhoneNumber(),
                user.getUserProfile().getFirstName(),
                user.getUserProfile().getLastName(),
                user.getUserProfile().getSurname(),
                user.getUserProfile().getBirthDate(),
                user.getUserProfile().getGender(),
                user.getUserProfile().getPhoto() != null ? Base64.getEncoder().encodeToString(user.getUserProfile().getPhoto()) : null,
                user.getUserProfile().getPhotoFormat(),
                user.getUserProfile().getHeight(),
                user.getUserProfile().getWeight(),
                RoleMapper.mapToRoleDto(user.getRole()),
                CityMapper.mapToCityDto(user.getUserProfile().getCity()),
                TeamMapper.mapToTeamDto(user.getUserProfile().getTeam()),
                TrophyMapper.mapToTrophyDtoList(user.getUserProfile().getTrophies()),
                SocialNetworkMapper.mapToSocialNetworkDtoList(user.getUserProfile().getSocialNetworks())
        );
    }
}
