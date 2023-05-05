package LosGuerreros.microserviceclients.service;

import LosGuerreros.microserviceclients.Model.Client;
import LosGuerreros.microserviceclients.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveClient(Client credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        clientRepository.save(credential);
        return "user added to the system";
    }

    public String generateToken(Client client) {
        return jwtService.generateToken(client);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

    public boolean existingClientByLogin(String login, String password) {
        Client client = clientRepository.findByLogin(login).orElse(null);
        if(client == null) return false;
        return this.passwordEncoder.matches(password, client.getPassword());
    }

}