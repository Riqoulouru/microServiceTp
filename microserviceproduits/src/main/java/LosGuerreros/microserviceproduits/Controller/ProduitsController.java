package LosGuerreros.microserviceproduits.Controller;


import LosGuerreros.microserviceproduits.Model.Produits;
import LosGuerreros.microserviceproduits.Repository.ProduitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/clients")
public class ProduitsController {

    @Autowired
    private ProduitsRepository produitsRepository;

    @GetMapping("/all")
    public Page<Produits> getAllClient(@RequestParam("page") int page, @RequestParam("number") int number) {
        return produitsRepository.findAll(PageRequest.of(page, number));
    }

}
