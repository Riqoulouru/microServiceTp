package LosGuerreros.microservicepaiements.Controller;

import LosGuerreros.microservicepaiements.Model.Paiements;
import LosGuerreros.microservicepaiements.Repository.PaiementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/clients")
public class PaiementsController {

    @Autowired
    private PaiementsRepository paiementsRepository;

    @GetMapping("/all")
    public Page<Paiements> getAllClient(@RequestParam("page") int page, @RequestParam("number") int number) {
        return paiementsRepository.findAll(PageRequest.of(page, number));
    }


    @PostMapping("/save")
    public @ResponseBody Paiements getAllClient(@RequestBody Paiements paiements) {
        return paiementsRepository.save(paiements);
    }

}
