package document.apidocument.repository;

import document.apidocument.domain.Project;
import document.apidocument.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByUsersContaining(User user);
}
