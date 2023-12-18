package pt.gmartins.employee.manager.domain.user;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pt.gmartins.employee.manager.domain.job.Job;
import pt.gmartins.employee.manager.domain.user.UserRole;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Table(name ="users")
@Entity(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    //@Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private Date date_of_birth;
    private String phone_number;
    private Integer ssn;
    private Integer nif;
    private String address;
    private String zip_code;
    private String city;
    private String state;

    private String login;
    private String password;
    private UserRole role;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    private Boolean active;

    public User(String login,String password,String firstName,String lastName,
                Date date_of_birth,String phone_number,Integer ssn,
                Integer nif,String address,String zip_code ,
                String city, String state, UserRole role){
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date_of_birth = date_of_birth;
        this.phone_number = phone_number;
        this.ssn = ssn;
        this.nif = nif;
        this.address = address;
        this.zip_code = zip_code;
        this.city = city;
        this.state = state;
        this.role = role;
        this.active = true;
    }

    public User(String login,String password,String firstName,String lastName,
                Date date_of_birth,String phone_number,Integer ssn,
                Integer nif,String address,String zip_code ,
                String city, String state, UserRole role, Job job){
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date_of_birth = date_of_birth;
        this.phone_number = phone_number;
        this.ssn = ssn;
        this.nif = nif;
        this.address = address;
        this.zip_code = zip_code;
        this.city = city;
        this.state = state;
        this.role = role;
        this.job = job;
        this.active = true;
    }

    public User(RegisterDTO data){
        this.login = data.getLogin();
        this.password = data.getPassword();
        this.firstName = data.getFirstName();
        this.lastName = data.getLastName();
        this.date_of_birth = data.getDate_of_birth();
        this.phone_number = data.getPhone_number();
        this.ssn = data.getSsn();
        this.nif = data.getNif();
        this.address = data.getAddress();
        this.zip_code = data.getZip_code();
        this.city = data.getCity();
        this.state = data.getState();
        this.role = data.getRole();
        this.active = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername(){
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
