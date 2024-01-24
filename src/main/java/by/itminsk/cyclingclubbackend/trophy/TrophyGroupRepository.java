package by.itminsk.cyclingclubbackend.trophy;

import by.itminsk.cyclingclubbackend.trophy.TrophyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrophyGroupRepository extends JpaRepository<TrophyGroup, Long> {

}
