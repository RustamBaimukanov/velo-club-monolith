package by.itminsk.cyclingclubbackend.role;

import by.itminsk.cyclingclubbackend.role.dto.RoleDto;
import by.itminsk.cyclingclubbackend.role.dto.RoleEnum;
import by.itminsk.cyclingclubbackend.role.dto.RolesEnum;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
@Hidden
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByName(RoleEnum name);

    Set<Role> findRolesByNameIn(Set<RoleEnum> names);

    List<RoleDto> findAllByIdIsNot(Long id);

    List<Role> findAllByNameNot(RoleEnum roleEnum);

    @Query(value = "select r.qualification from roles r where r.id = (select u.role_id from users u where u.id=?1)", nativeQuery = true)
    String findRoleByUser(Long userId);

    //Role findByRoleName(String roleName);

}
