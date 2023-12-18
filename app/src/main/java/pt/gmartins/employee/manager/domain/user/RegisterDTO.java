package pt.gmartins.employee.manager.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pt.gmartins.employee.manager.domain.job.Job;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private Date date_of_birth;
    private String phone_number;
    private Integer ssn;
    private Integer nif;
    private String address;
    private String zip_code;
    private String city;
    private String state;
    private UserRole role;



}
