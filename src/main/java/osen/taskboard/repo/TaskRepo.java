package osen.taskboard.repo;

import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.data.jpa.repository.JpaRepository;
import osen.taskboard.domain.Task;

public interface TaskRepo extends JpaRepository<Task, Long> {
    Task findFirstById(Long id);
    Task findFirstByUuid(String uuid);
    Iterable<Task> findAllByParentUuid(String uuid);
    Iterable<Task> findAllByUserUuid(String uuid);
}
