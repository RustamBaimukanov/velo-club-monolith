package com.work.veloclub.repository.user;

import com.work.veloclub.model.role.RoleDto;
import com.work.veloclub.model.role.RoleEnum;
import com.work.veloclub.model.user.*;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Hidden
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    Boolean existsByEmailAndEmailIsNotNull(String email);


    Optional<User> findUserByPhoneNumber(String phoneNumber);

    UserInfoDTO getUserById(Long id);

    UserInfoDTO getUserByPhoneNumber(String phoneNumber);

    EditUserDTO findByPhoneNumber(String phoneNumber);

    UserMenuDTO getUserMenuByPhoneNumber(String phoneNumber);



    @Query(value = "select u.id from User u where u.phoneNumber=?1")
    Long getIdFromPhoneNumber(String phoneNumber);


    Boolean existsByPhoneNumber(String phoneNumber);

    Boolean existsByPhoneNumberAndEmail(String phoneNumber, String email);


    Boolean existsByEmail(String email);


    Optional<User> findUserByEmailAndPassword(String email, String password);

    @Query("select new com.work.veloclub.model.user.UserGetDto(u.id, u.email, u.firstName, u.lastName) from User u where u.id = ?1")
    Optional<UserGetDto> findUserDtoById(Long id);

    @Query("select new com.work.veloclub.model.user.UserGetDto(u.id, u.email, u.firstName, u.lastName) from User u")
    List<UserGetDto> findAllUserDto();

    List<UserGetDto> findAllByRoleName(RoleEnum role);


    List<UserGetDto> findAllByRoleNameNot(RoleEnum role);

    Set<User> findAllByIdIn(List<Long> ids);

    Boolean existsByIdIn(Set<Long> ids);

    @Query("select new com.work.veloclub.model.role.RoleDto(u.role.id, u.role.name, u.role.qualification) from User u where u.id = ?1")
    RoleDto findRoleByUserId(Long id);

    @Query("select new com.work.veloclub.model.user.UserGetDto(u.id, u.email, u.firstName, u.lastName) from User u where u.team.id = ?1")
    List<UserGetDto> findUsersByTeamId(Long id);

}
