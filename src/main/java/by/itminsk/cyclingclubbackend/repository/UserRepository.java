package by.itminsk.cyclingclubbackend.repository;

import by.itminsk.cyclingclubbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
