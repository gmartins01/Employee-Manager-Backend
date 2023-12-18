package pt.gmartins.employee.manager.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDTO {
    private String login;
    private String password;
}
