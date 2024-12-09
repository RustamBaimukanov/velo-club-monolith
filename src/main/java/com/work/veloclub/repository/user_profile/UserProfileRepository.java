package com.work.veloclub.repository.user_profile;

import com.work.veloclub.model.user_profile.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    Boolean existsByEmail(String email);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM UserProfile u WHERE u.email = :email AND u.id <> :id")
    Boolean existsByEmailAndNotId(@Param("id") Long id, @Param("email") String email);

    @Query("SELECT u FROM UserProfile u WHERE u.user.id = :userId")
    UserProfile findUserProfileByUserId(@Param("userId") Long userId);

    List<UserProfile> findAllByTeamId(Long teamId);




}
