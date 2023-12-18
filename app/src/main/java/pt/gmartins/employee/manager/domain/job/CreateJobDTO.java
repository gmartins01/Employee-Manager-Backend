package pt.gmartins.employee.manager.domain.job;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobDTO {

    @NotBlank
    private String name;

    private String description;


    public CreateJobDTO(Job job){
        this.name = job.getName();
        this.description = job.getDescription();
    }
}
