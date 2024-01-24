package by.itminsk.cyclingclubbackend.trophy;

import by.itminsk.cyclingclubbackend.trophy.TrophyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrophyTypeRepository extends JpaRepository<TrophyType, Long> {
}
