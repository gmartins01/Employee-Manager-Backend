package pt.gmartins.employee.manager.services;

import org.springframework.stereotype.Service;
import pt.gmartins.employee.manager.domain.job.CreateJobDTO;
import pt.gmartins.employee.manager.domain.job.Job;
import pt.gmartins.employee.manager.domain.job.JobDTO;
import pt.gmartins.employee.manager.domain.job.UpdateJobDTO;
import pt.gmartins.employee.manager.repositories.JobRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JobService {

    private static final int PAGE_SIZE = 10;

    private final JobRepository jobRepository;


    public JobService(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }

    public List<JobDTO> getAllJobs(){
        return this.jobRepository.findAllByActiveTrue().stream().map(JobDTO::new)
                .collect(Collectors.toList());
    }

    public void createJob(CreateJobDTO data){
        //if(this.jobRepository.findByName(data.getName()) !=null) return ResponseEntity.badRequest().build();

        Job newJob = new Job(data.getName(),data.getDescription());
        this.jobRepository.save(newJob);
    }

    public Job updateJob(UpdateJobDTO data)  {
        Optional<Job> optionalJob = this.jobRepository.findById(data.getId());

        if(optionalJob.isPresent()) {

            Job job = optionalJob.get();
            job.setName(data.getName());
            job.setDescription(data.getDescription());

            return this.jobRepository.save(job);

        }else{
            throw new EntityNotFoundException();
        }
    }

    public void deleteJob(UUID id) {
        Optional<Job> optionalJob = this.jobRepository.findById(id);

        if(optionalJob.isPresent()) {
            Job job = optionalJob.get();
            job.setActive(false);

            this.jobRepository.save(job);
        } else {
            throw new EntityNotFoundException();
        }
    }

}
