package pt.gmartins.employee.manager.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID id;
    private String login;
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

    
    public UserDTO(String login,UserRole role){
        this.login = login;
        this.role = role;
    }

    public UserDTO(User user){
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.date_of_birth = user.getDate_of_birth();
        this.phone_number = user.getPhone_number();
        this.ssn = user.getSsn();
        this.nif = user.getNif();
        this.address = user.getAddress();
        this.zip_code = user.getZip_code();
        this.city = user.getCity();
        this.state = user.getState();

        this.login = user.getLogin();
        this.role = user.getRole();
    }

}
