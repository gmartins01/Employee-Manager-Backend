package pt.gmartins.employee.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import pt.gmartins.employee.manager.domain.job.Job;
import pt.gmartins.employee.manager.domain.user.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    UserDetails findByLogin(String login);

    List<User> findAllByActiveTrue();
}
