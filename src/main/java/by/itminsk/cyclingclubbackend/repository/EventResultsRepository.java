package by.itminsk.cyclingclubbackend.repository;

import by.itminsk.cyclingclubbackend.model.user.EventResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface EventResultsRepository extends JpaRepository<EventResult, Long> {

    Set<EventResult> findAllByUserId(Long userId);

//    @Query("SELECT DISTINCT YEAR(e.date) FROM EventResult e where e.user.id = ?1")
//    List<Integer> findAllYearsFromEventResultsByUserId(Long userId);
}
