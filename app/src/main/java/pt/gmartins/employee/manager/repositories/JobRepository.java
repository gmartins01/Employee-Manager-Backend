package pt.gmartins.employee.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.gmartins.employee.manager.domain.job.Job;

import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID> {
    Job findByName(String name);

    List<Job> findAllByActiveTrue();

}
