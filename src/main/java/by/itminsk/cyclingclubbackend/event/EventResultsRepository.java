package by.itminsk.cyclingclubbackend.event;

import by.itminsk.cyclingclubbackend.event.EventResult;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
@Hidden
public interface EventResultsRepository extends JpaRepository<EventResult, Long> {

    Set<EventResult> findAllByUserId(Long userId);

    List<EventResult> findAllByEventId(Long eventId);


//    @Query("SELECT DISTINCT YEAR(e.date) FROM EventResult e where e.user.id = ?1")
//    List<Integer> findAllYearsFromEventResultsByUserId(Long userId);
}
