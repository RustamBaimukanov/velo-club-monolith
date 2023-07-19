package by.itminsk.cyclingclubbackend.repository;

import by.itminsk.cyclingclubbackend.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByName(String name);

    List<Role> findAllByIdIsNot(Integer id);

    //Role findByRoleName(String roleName);

}
