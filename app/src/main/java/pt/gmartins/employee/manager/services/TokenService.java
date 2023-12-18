package pt.gmartins.employee.manager.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pt.gmartins.employee.manager.domain.user.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    public String generateToken(User user){
        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);
            Instant expirationDate = generateExpirationDate();

            return JWT.create()
                    .withIssuer("employee-manager")
                    .withSubject(user.getLogin())
                    .withClaim("role", user.getRole().ordinal())
                    .withExpiresAt(expirationDate)
                    .sign(algorithm);
        }catch (JWTCreationException exception){
            throw new RuntimeException("Error while generating token",exception);
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("employee-manager")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception){
            return "";
        }
    }

    private Instant generateExpirationDate() {
        // Calculate the expiration time as one hour from the current time
        return Instant.now().plusSeconds(3600);
    }
}
