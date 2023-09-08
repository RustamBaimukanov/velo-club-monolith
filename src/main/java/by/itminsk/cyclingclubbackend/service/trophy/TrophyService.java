package by.itminsk.cyclingclubbackend.service.trophy;

import by.itminsk.cyclingclubbackend.model.user.Trophy;
import by.itminsk.cyclingclubbackend.model.user.User;

import java.util.Set;

public interface TrophyService {

    void addTrophy(Long groupId, Long typeId, User user, String description);

    Set<Trophy> findAllByUserId(Long userId);

    Set<Trophy> findAllByPhoneNumber(String phoneNumber);

}
