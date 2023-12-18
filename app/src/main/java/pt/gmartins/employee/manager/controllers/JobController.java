package pt.gmartins.employee.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pt.gmartins.employee.manager.domain.job.CreateJobDTO;
import pt.gmartins.employee.manager.domain.job.Job;
import pt.gmartins.employee.manager.domain.job.JobDTO;

import pt.gmartins.employee.manager.domain.job.UpdateJobDTO;
import pt.gmartins.employee.manager.repositories.JobRepository;
import pt.gmartins.employee.manager.services.JobService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService){
        this.jobService = jobService;
    }

    @Autowired
    private JobRepository repository;

    @GetMapping
    private List<JobDTO> getAllJobs(){
        return this.jobService.getAllJobs();
    }

    @PostMapping
    private ResponseEntity<CreateJobDTO> createJob(@RequestBody @Valid CreateJobDTO data){
        this.jobService.createJob(data);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    private ResponseEntity<Job> updateJob(@RequestBody @Valid UpdateJobDTO data) {
        Job updatedJob = this.jobService.updateJob(data);

        if(updatedJob != null){
            return ResponseEntity.ok(updatedJob);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteJob(@PathVariable UUID id){
        this.jobService.deleteJob(id);

        return ResponseEntity.ok().build();
    }

}
