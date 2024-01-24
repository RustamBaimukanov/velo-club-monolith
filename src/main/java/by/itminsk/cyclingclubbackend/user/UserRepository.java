package by.itminsk.cyclingclubbackend.user;

import by.itminsk.cyclingclubbackend.user.dto.EditUserDTO;
import by.itminsk.cyclingclubbackend.user.dto.UserInfoDTO;
import by.itminsk.cyclingclubbackend.user.dto.UserMenuDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
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

}
