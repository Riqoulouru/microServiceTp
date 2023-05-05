package LosGuerreros.microservicepaiements.Controller;

import LosGuerreros.microservicepaiements.Model.Paiement;
import LosGuerreros.microservicepaiements.Repository.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/paiements")
public class PaiementsController {

    @Autowired
    private PaiementRepository paiementRepository;

    @GetMapping("/all")
    public Page<Paiement> getAllPaiements(@RequestParam("page") int page, @RequestParam("number") int number) {
        return paiementRepository.findAll(PageRequest.of(page, number, Sort.by("date").descending()));
    }


    @PostMapping("/save")
    public @ResponseBody Paiement savePaiement(@RequestBody Paiement paiement) {
        return paiementRepository.save(paiement);
    }

}
