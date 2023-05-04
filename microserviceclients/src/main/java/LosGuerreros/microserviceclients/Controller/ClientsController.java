package LosGuerreros.microserviceclients.Controller;

import LosGuerreros.microserviceclients.Model.Clients;
import LosGuerreros.microserviceclients.Repository.ClientsRepository;
import LosGuerreros.microserviceclients.dto.AuthRequest;
import LosGuerreros.microserviceclients.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/clients")
public class ClientsController {

    @Autowired
    private ClientsRepository clientsRepository;
    @Autowired
    private AuthService service;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/all")
    public Page<Clients> getAllClient(@RequestParam("page") int page, @RequestParam("number") int number) {
        return clientsRepository.findAll(PageRequest.of(page, number));
    }

    @PostMapping("/save")
    public @ResponseBody Clients getAllClient(@RequestBody Clients clients) {
        return clientsRepository.save(clients);
    }

    @PostMapping("/register")
    public String addNewUser(@RequestBody Clients user) {
        return service.saveClient(user);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return service.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }

}
