package LosGuerreros.microservicecommandes.Controller;


import LosGuerreros.microservicecommandes.Model.Commande;
import LosGuerreros.microservicecommandes.Model.CommandeComposition;
import LosGuerreros.microservicecommandes.Model.CommandeCompositionId;
import LosGuerreros.microservicecommandes.Repository.CommandeRepository;
import LosGuerreros.microservicecommandes.dto.CommandeDto;
import LosGuerreros.microservicecommandes.mapper.CommandeMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/all/{login}")
    public ResponseEntity<List<Commande>> getAllByLogin(@PathVariable("login") String login) {
        return ResponseEntity.ok(commandeRepository.findAllByLogin(login));
    }

    @PostMapping("/save")
    public @ResponseBody Commande saveCommande(@RequestBody Commande commande) {
        return commandeRepository.save(commande);
    }
    
    @PostMapping("/validate/{login}/{idPaiement}")
    public @ResponseBody Integer saveCommande(@PathVariable("login") String login, @PathVariable("idPaiement") Integer idPaiement, @RequestBody List<Integer> idProduits) {
        Commande commande = new Commande();
        commande.setLogin(login);
        commande.setIdPaiement(idPaiement);
        commande = commandeRepository.save(commande);

        List<CommandeComposition> compositions = new ArrayList<>();
        for (Integer idProduit : idProduits) {
            CommandeComposition compo = new CommandeComposition();
            compo.setCommande(commande);
            compo.setCommandeCompositionId(new CommandeCompositionId(commande.getIdCommande(), idProduit));
            compositions.add(compo);
        }
        commande.setCommandeCompositions(compositions);
        commande = commandeRepository.save(commande);
        return commande.getIdCommande();
    }

}
