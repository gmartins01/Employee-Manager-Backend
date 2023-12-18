package pt.gmartins.employee.manager.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import pt.gmartins.employee.manager.domain.job.CreateJobDTO;
import pt.gmartins.employee.manager.domain.job.Job;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class JobRepositoryTest {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get Job successfully from DB")
    void findByNameCase1() {
        String name = "teste";

        CreateJobDTO data = new CreateJobDTO(name,"teste");

        this.createJob(data);

        Optional<Job> result = Optional.ofNullable(this.jobRepository.findByName(name));

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get Job from DB when job does not exists")
    void findByNameCase2() {
        String name = "teste";

        Optional<Job> result = Optional.ofNullable(this.jobRepository.findByName(name));

        assertThat(result.isEmpty()).isTrue();
    }


    private Job createJob(CreateJobDTO data){
        Job newJob = new Job(data);
        this.entityManager.persist(newJob);
        return newJob;
    }
}