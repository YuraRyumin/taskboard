package osen.taskboard.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import osen.taskboard.domain.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findFirstById(Long id);
    Role findFirstByName(String name);
}
