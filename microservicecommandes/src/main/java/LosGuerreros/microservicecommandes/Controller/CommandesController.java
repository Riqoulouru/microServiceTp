package LosGuerreros.microservicecommandes.Controller;


import LosGuerreros.microservicecommandes.Model.Commande;
import LosGuerreros.microservicecommandes.Repository.CommandeRepository;
import LosGuerreros.microservicecommandes.dto.CommandeDto;
import LosGuerreros.microservicecommandes.mapper.CommandeMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/commandes")
public class CommandesController {

    @Autowired
    private CommandeRepository commandeRepository;

    @Resource
    CommandeMapper commandeMapper;

    @GetMapping("/all")
    public Page<CommandeDto> getAllCommande(@RequestParam("page") int page, @RequestParam("number") int number) {
        Pageable pageable = PageRequest.of(page, number);
        return commandeMapper.mapCommandeToCommandeDto(commandeRepository.findAll(pageable), pageable);
    }

    @PostMapping("/save")
    public @ResponseBody Commande saveCommande(@RequestBody Commande commande) {
        return commandeRepository.save(commande);
    }

}
