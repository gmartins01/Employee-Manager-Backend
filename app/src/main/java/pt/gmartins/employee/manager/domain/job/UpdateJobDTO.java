package pt.gmartins.employee.manager.domain.job;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateJobDTO {

    private UUID id;

    @NotBlank
    private String name;
    private String description;

    public UpdateJobDTO(Job job){
        this.id = job.getId();
        this.name = job.getName();
        this.description = job.getDescription();
    }

}
