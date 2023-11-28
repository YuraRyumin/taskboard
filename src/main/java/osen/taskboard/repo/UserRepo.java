package osen.taskboard.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import osen.taskboard.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findFirstByLogin(String login);
    User findFirstByUuid(String uuid);
}
