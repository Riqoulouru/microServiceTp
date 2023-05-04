package LosGuerreros.microserviceclients.config;


import LosGuerreros.microserviceclients.Model.Clients;
import LosGuerreros.microserviceclients.Repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomClientDetailsService implements UserDetailsService {

    @Autowired
    private ClientsRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Clients> credential = repository.findByLogin(login);
        return credential.map(CustomClientDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found with login :" + login));
    }
}