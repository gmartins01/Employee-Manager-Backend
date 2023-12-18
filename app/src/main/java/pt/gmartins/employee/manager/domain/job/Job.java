package pt.gmartins.employee.manager.domain.job;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Table(name = "jobs")
@Entity(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Job {
    @Id
    //@Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    private String name;
    private String description;
    private Boolean active;

    public Job(String name, String description){
        this.name = name;
        this.description = description;
        this.active = true;
    }

    public Job(CreateJobDTO data) {
        this.name = data.getName();
        this.description= data.getDescription();
    }
}
