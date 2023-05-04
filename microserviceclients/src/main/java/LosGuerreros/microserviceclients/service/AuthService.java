package LosGuerreros.microserviceclients.service;

import LosGuerreros.microserviceclients.Model.Clients;
import LosGuerreros.microserviceclients.Repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private ClientsRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveClient(Clients credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        repository.save(credential);
        return "user added to the system";
    }

    public String generateToken(String login) {
        return jwtService.generateToken(login);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


}