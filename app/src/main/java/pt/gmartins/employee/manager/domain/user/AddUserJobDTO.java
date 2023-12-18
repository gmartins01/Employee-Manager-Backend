package pt.gmartins.employee.manager.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddUserJobDTO {

    private UUID userId;
    private UUID jobId;

}
