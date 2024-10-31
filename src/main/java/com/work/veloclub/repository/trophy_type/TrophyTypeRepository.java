package com.work.veloclub.repository.trophy_type;

import com.work.veloclub.model.trophy_type.TrophyType;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Hidden
public interface TrophyTypeRepository extends JpaRepository<TrophyType, Long> {
}
