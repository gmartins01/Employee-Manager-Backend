package pt.gmartins.employee.manager.domain.job;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobDTO {

    private String id;
    private String name;
    private String description;
    private Boolean active;

    public JobDTO(Job job){
        this.id = String.valueOf(job.getId());
        this.name = job.getName();
        this.description = job.getDescription();
        this.active = job.getActive();
    }

}
