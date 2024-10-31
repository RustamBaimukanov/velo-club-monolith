package com.work.veloclub.repository.race;

import com.work.veloclub.model.race.Race;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Hidden
public interface RaceRepository extends JpaRepository<Race, Long> {

}
