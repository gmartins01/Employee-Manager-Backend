package pt.gmartins.employee.manager.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import pt.gmartins.employee.manager.domain.job.Job;
import pt.gmartins.employee.manager.domain.job.UpdateJobDTO;
import pt.gmartins.employee.manager.repositories.JobRepository;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JobServiceTest {

    @Mock
    private JobRepository jobRepository;

    @Autowired
    @InjectMocks
    private JobService jobService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should get all Jobs successfully from DB")
    void getAllJobsCase1() {
    }

    @Test
    @DisplayName("Should get all Jobs successfully from DB")
    void getAllJobsCase2() {
    }

    @Test
    void createJob() {
    }

    @Test
    @DisplayName("Should update the job successfully")
    void updateJobCase1() {
        UUID jobId = UUID.randomUUID();
        Job job = new Job();
        job.setId(jobId);
        job.setName("Old Name");
        job.setDescription("Old Description");

        UpdateJobDTO updateJobDTO = new UpdateJobDTO();
        updateJobDTO.setId(jobId);
        updateJobDTO.setName("New Name");
        updateJobDTO.setDescription("New Description");

        when(jobRepository.findById(jobId)).thenReturn(Optional.of(job));
        when(jobRepository.save(any(Job.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Job updatedJob = jobService.updateJob(updateJobDTO);

        verify(jobRepository, times(1)).findById(jobId);
        verify(jobRepository, times(1)).save(any(Job.class));

        assertNotNull(updatedJob);
        assertEquals(updateJobDTO.getName(), updatedJob.getName());
        assertEquals(updateJobDTO.getDescription(), updatedJob.getDescription());
        assertEquals(jobId, updatedJob.getId());

    }

    @Test
    @DisplayName("Should return null and not update any job")
    void updateJobCase2() throws Exception {
        UpdateJobDTO updateJobDTO = new UpdateJobDTO();
        UUID nonExistentId = UUID.randomUUID();

        updateJobDTO.setId(nonExistentId);

        when(jobRepository.findById(eq(nonExistentId))).thenReturn(Optional.empty());

        Job updatedJob = jobService.updateJob(updateJobDTO);

        assertNull(updatedJob);
        verify(jobRepository, times(1)).findById(any(UUID.class));
        verifyNoMoreInteractions(jobRepository);

    }
}