package by.itminsk.cyclingclubbackend.trophy;

import by.itminsk.cyclingclubbackend.trophy.Trophy;
import by.itminsk.cyclingclubbackend.user.User;

import java.util.Set;

public interface TrophyService {

    void addTrophy(Long groupId, Long typeId, User user, String description);

    Set<Trophy> findAllByUserId(Long userId);

    Set<Trophy> findAllByPhoneNumber(String phoneNumber);

}
