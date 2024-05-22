package by.itminsk.cyclingclubbackend.role;

import by.itminsk.cyclingclubbackend.role.dto.RoleDto;
import by.itminsk.cyclingclubbackend.role.dto.RoleEnum;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Hidden
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByName(RoleEnum name);

    List<RoleDto> findAllByIdIsNot(Long id);

    @Query(value = "select r.qualification from roles r where r.id = (select ur.role_id from users_roles ur where user_id=?1)", nativeQuery = true)
    String findRoleByUser(Long userId);

    //Role findByRoleName(String roleName);

}
