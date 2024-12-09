package com.work.veloclub.repository.role;

import com.work.veloclub.model.role.RoleDtoDeprecated;
import com.work.veloclub.model.role.RoleEnum;
import com.work.veloclub.model.role.Role;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
@Hidden
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByName(RoleEnum name);

    Set<Role> findRolesByNameIn(Set<RoleEnum> names);

    List<RoleDtoDeprecated> findAllByIdIsNot(Long id);

    List<Role> findAllByNameNot(RoleEnum roleEnum);

    @Query(value = "select r.qualification from roles r where r.id = (select u.role_id from users u where u.id=?1)", nativeQuery = true)
    String findRoleByUser(Long userId);

    //Role findByRoleName(String roleName);

}
