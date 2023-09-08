package by.itminsk.cyclingclubbackend.repository;

import by.itminsk.cyclingclubbackend.model.user.Trophy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Set;

@EnableJpaRepositories
@Repository
public interface TrophyRepository extends JpaRepository<Trophy, Long> {

    Set<Trophy> findAllByUserId(Long userId);

}
