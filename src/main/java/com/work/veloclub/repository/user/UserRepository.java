package com.work.veloclub.repository.user;

import com.work.veloclub.model.user.User;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Hidden
public interface UserRepository extends JpaRepository<User, Long> {

//    Optional<User> findUserByEmail(String email);
//
//    Boolean existsByEmailAndEmailIsNotNull(String email);

    Optional<User> findUserById(Long id);

    Optional<User> findUserByPhoneNumber(String phoneNumber);

    @Query(value = "select u from User u left join fetch u.role where u.phoneNumber = :phoneNumber")
    Optional<User> findUserWithRoleByPhoneNumber(String phoneNumber);


    @Query(value =
            """
                            select u from User u
                             left join fetch u.role
                             left join fetch u.userProfile up
                             left join fetch up.city
                             left join fetch up.team
                             where u.phoneNumber = :phoneNumber
                    """)
    Optional<User> findUserWithProfileByPhoneNumber(@Param("phoneNumber") String phoneNumber);


    @EntityGraph(attributePaths = {"userProfile", "userProfile.socialNetworks"})
    @Query(value =
            """
                            select u from User u
                             left join fetch u.role
                             left join fetch u.userProfile up
                             left join fetch up.city
                             left join fetch up.team
                             where u.phoneNumber = :phoneNumber
                    """)
    Optional<User> findUserWithProfileAndSocialNetworksByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @EntityGraph(attributePaths = {"userProfile", "userProfile.socialNetworks"})
    @Query(value =
            """
                            select u from User u
                             left join fetch u.role
                             left join fetch u.userProfile up
                             left join fetch up.city
                             left join fetch up.team
                             where u.id = :id
                    """)
    Optional<User> findUserWithProfileAndSocialNetworksById(@Param("id") Long id);

    @Query(value =
            """
                            select u from User u
                             left join fetch u.role
                             left join fetch u.userProfile up
                             left join fetch up.city
                             left join fetch up.team
                             where u.id = :id
                    """)
    Optional<User> findUserWithProfileById(@Param("id") Long id);


//    UserInfoDTO getUserById(Long id);
//
//    UserInfoDTO getUserByPhoneNumber(String phoneNumber);

//    EditUserDTO findByPhoneNumber(String phoneNumber);

//    UserMenuDTO getUserMenuByPhoneNumber(String phoneNumber);


    @Query(value = "select u.id from User u where u.phoneNumber=?1")
    Long getIdFromPhoneNumber(String phoneNumber);


    Boolean existsByPhoneNumber(String phoneNumber);

//    Boolean existsByPhoneNumberAndEmail(String phoneNumber, String email);
//
//
//    Boolean existsByEmail(String email);


//    Optional<User> findUserByEmailAndPassword(String email, String password);

//    @Query("select new com.work.veloclub.model.user.UserGetDto(u.id, u.email, u.firstName, u.lastName) from User u where u.id = ?1")
//    Optional<UserGetDto> findUserDtoById(Long id);
//
//    @Query("select new com.work.veloclub.model.user.UserGetDto(u.id, u.email, u.firstName, u.lastName) from User u")
//    List<UserGetDto> findAllUserDto();

//    List<UserGetDto> findAllByRoleName(RoleEnum role);
//
//
//    List<UserGetDto> findAllByRoleNameNot(RoleEnum role);
//
//    Set<User> findAllByIdIn(List<Long> ids);
//
//    Boolean existsByIdIn(Set<Long> ids);

//    @Query("select new com.work.veloclub.model.role.RoleDto(u.role.id, u.role.name, u.role.qualification) from User u where u.id = ?1")
//    RoleDto findRoleByUserId(Long id);
//
//    @Query("select new com.work.veloclub.model.user.UserGetDto(u.id, u.email, u.firstName, u.lastName) from User u where u.team.id = ?1")
//    List<UserGetDto> findUsersByTeamId(Long id);

    @Query(value = "select count(u) from User u left join u.role r where r.id = :roleId")
    Integer countUserWithRoleByRoleId(Long roleId);

}
