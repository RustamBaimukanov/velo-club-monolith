package by.itminsk.cyclingclubbackend.trophy;

import by.itminsk.cyclingclubbackend.trophy.TrophyGroup;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Hidden
public interface TrophyGroupRepository extends JpaRepository<TrophyGroup, Long> {

}
