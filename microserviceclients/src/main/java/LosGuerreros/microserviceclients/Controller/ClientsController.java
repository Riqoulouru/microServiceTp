package LosGuerreros.microserviceclients.Controller;

import LosGuerreros.microserviceclients.Model.Clients;
import LosGuerreros.microserviceclients.Repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping(path = "/clients")
public class ClientsController {

    @Autowired
    private ClientsRepository clientsRepository;

    @GetMapping("/all")
    public Page<Clients> getAllClient(@RequestParam("page") int page, @RequestParam("number") int number) {
        return clientsRepository.findAll(PageRequest.of(page, number));
    }

    @PostMapping("/save")
    public @ResponseBody Clients getAllClient(@RequestBody Clients clients) {
        return clientsRepository.save(clients);
    }

}
