package pt.gmartins.employee.manager.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import pt.gmartins.employee.manager.domain.user.RegisterDTO;
import pt.gmartins.employee.manager.domain.user.User;
import pt.gmartins.employee.manager.domain.user.UserRole;
import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get User successfully from DB")
    void findByLoginCase1() {
        String login = "gmartins";

        LocalDate localDate = LocalDate.of(2001, 2, 3);
        Date date = Date.valueOf(localDate);

        RegisterDTO data = new RegisterDTO(login,"12344","Gonçalo","Martins",
                date,"98363633",111111,111111,
                "Rua dos Adimns","4905-544","Viana","Viana", UserRole.ADMIN);
        this.createUser(data);

        UserDetails result = this.userRepository.findByLogin(login);

        //assertThat(result.

    }

    private User createUser(RegisterDTO data){
        User newUser = new User(data);
        this.entityManager.persist(newUser);
        return newUser;
    }
}