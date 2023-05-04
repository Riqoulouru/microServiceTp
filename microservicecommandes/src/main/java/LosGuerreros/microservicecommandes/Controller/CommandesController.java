package LosGuerreros.microservicecommandes.Controller;


import LosGuerreros.microservicecommandes.Model.Commandes;
import LosGuerreros.microservicecommandes.Repository.CommandesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/clients")
public class CommandesController {

    @Autowired
    private CommandesRepository commandesRepository;

    @GetMapping("/all")
    public Page<Commandes> getAllClient(@RequestParam("page") int page, @RequestParam("number") int number) {
        return commandesRepository.findAll(PageRequest.of(page, number));
    }

}
