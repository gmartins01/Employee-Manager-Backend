package pt.gmartins.employee.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.gmartins.employee.manager.domain.user.AuthenticationDTO;
import pt.gmartins.employee.manager.domain.user.LoginResponseDTO;
import pt.gmartins.employee.manager.domain.user.RegisterDTO;
import pt.gmartins.employee.manager.domain.user.User;
import pt.gmartins.employee.manager.repositories.UserRepository;
import pt.gmartins.employee.manager.services.TokenService;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(),data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public  ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByLogin(data.getLogin()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        User newUser = new User(data.getLogin(), encryptedPassword,data.getFirstName(),data.getLastName(),
                data.getDate_of_birth(),data.getPhone_number(),data.getSsn(),data.getNif(),data.getAddress(),
                data.getZip_code(), data.getCity(), data.getState(), data.getRole());
        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
