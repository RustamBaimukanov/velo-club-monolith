package by.itminsk.cyclingclubbackend.user;

import by.itminsk.cyclingclubbackend.role.dto.RoleEnum;
import by.itminsk.cyclingclubbackend.user.dto.EditUserDTO;
import by.itminsk.cyclingclubbackend.user.dto.UserGetDto;
import by.itminsk.cyclingclubbackend.user.dto.UserInfoDTO;
import by.itminsk.cyclingclubbackend.user.dto.UserMenuDTO;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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

    UserInfoDTO getUserByPhoneNumber(String phoneNumber);

    EditUserDTO findByPhoneNumber(String phoneNumber);

    UserMenuDTO getUserMenuByPhoneNumber(String phoneNumber);



    @Query(value = "select u.id from User u where u.phoneNumber=?1")
    Long getIdFromPhoneNumber(String phoneNumber);


    Boolean existsByPhoneNumber(String phoneNumber);

    Boolean existsByPhoneNumberAndEmail(String phoneNumber, String email);


    Boolean existsByEmail(String email);


    Optional<User> findUserByEmailAndPassword(String email, String password);

    List<UserGetDto> findAllByRoleNameNot(RoleEnum role);

    Set<User> findAllByIdIn(List<Long> ids);

    Boolean existsByIdIn(Set<Long> ids);

}
