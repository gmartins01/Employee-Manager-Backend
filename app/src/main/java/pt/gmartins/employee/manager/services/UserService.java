package pt.gmartins.employee.manager.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pt.gmartins.employee.manager.domain.job.Job;
import pt.gmartins.employee.manager.domain.job.JobDTO;
import pt.gmartins.employee.manager.domain.user.AddUserJobDTO;
import pt.gmartins.employee.manager.domain.user.UpdateUserDTO;
import pt.gmartins.employee.manager.domain.user.User;
import pt.gmartins.employee.manager.domain.user.UserDTO;
import pt.gmartins.employee.manager.repositories.JobRepository;
import pt.gmartins.employee.manager.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public UserService(UserRepository userRepository,JobRepository jobRepository){
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }

    public List<UserDTO> getAllUsers(){
        return this.userRepository.findAllByActiveTrue().stream().map(UserDTO::new)
                .collect(Collectors.toList());
    }

    public User updateUser(UpdateUserDTO data){
        Optional<User> optionalUser = this.userRepository.findById(data.getId());

        if(optionalUser.isPresent()){
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
            User user = optionalUser.get();

            user.setFirstName(data.getFirstName());
            user.setLastName(data.getLastName());
            user.setDate_of_birth(data.getDate_of_birth());
            user.setPhone_number(data.getPhone_number());
            user.setSsn(data.getSsn());
            user.setNif(data.getNif());
            user.setAddress(data.getAddress());
            user.setCity(data.getCity());
            user.setZip_code(data.getZip_code());
            user.setState(data.getState());

            user.setLogin(data.getLogin());
            user.setPassword(encryptedPassword);
            user.setRole(data.getRole());

            return this.userRepository.save(user);
        }else {
            throw new EntityNotFoundException();
        }
    }

    public void deleteUser(UUID id){
        Optional<User> optionalUser = this.userRepository.findById(id);

        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setActive(false);

            this.userRepository.save(user);
        }else {
            throw new EntityNotFoundException();
        }
    }

    public Optional<Job> getJobByUser(UUID id){
        Optional<User> optionalUser = this.userRepository.findById(id);


        if(optionalUser.isPresent()){
            User user = optionalUser.get();

            Optional<Job> optionalJob = this.jobRepository.findById(user.getJob().getId());

            if(optionalJob.isPresent()){
                Job job = optionalJob.get();

                return this.jobRepository.findById(job.getId());
            }else{
                throw new EntityNotFoundException();
            }
        }else{
            throw new EntityNotFoundException();
        }
    }

    public User addUserJob(AddUserJobDTO data){
        Optional<Job> optionalJob = this.jobRepository.findById(data.getJobId());

        if (optionalJob.isPresent()){
            Job job = optionalJob.get();

            Optional<User> optionalUser = this.userRepository.findById(data.getUserId());

            if (optionalUser.isPresent()){
                User user = optionalUser.get();

                user.setJob(job);

                return this.userRepository.save(user);

            }else {
                throw new EntityNotFoundException();
            }
        }else{
            throw new EntityNotFoundException();
        }
    }
}
