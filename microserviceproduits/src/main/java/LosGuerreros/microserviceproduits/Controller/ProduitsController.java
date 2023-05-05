package LosGuerreros.microserviceproduits.Controller;


import LosGuerreros.microserviceproduits.Model.Produit;
import LosGuerreros.microserviceproduits.Repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/produits")
public class ProduitsController {

    @Autowired
    private ProduitRepository produitRepository;


    @GetMapping("/all")
    public Page<Produit> getAllProduit(@RequestParam("page") int page, @RequestParam("number") int number) {
        return produitRepository.findAll(PageRequest.of(page, number, Sort.by("insertDate").descending()));
    }

    @PostMapping("/save")
    public @ResponseBody Produit saveProduit(@RequestBody Produit produit) {
        return produitRepository.save(produit);
    }

    @GetMapping("/home/example")
    public  ResponseEntity<Map<String, Page<Produit>>> getAllProduitByCategoryForHomePage() {
        List<String> allCategory = produitRepository.findAllCategory();
        Map<String, Page<Produit>> firstProduitForEachCategory = new HashMap<>();
        allCategory.forEach(category -> firstProduitForEachCategory.put(category, produitRepository.findAllByCategory(category, PageRequest.of(0, 20, Sort.by("insertDate").descending()))));
        return ResponseEntity.ok(firstProduitForEachCategory);
    }

    @GetMapping("/home/search")
    public  ResponseEntity<Page<Produit>> getProduitBySearch(@RequestParam("page") Integer page,
                                                             @RequestParam("numberPerPage") Integer number,
                                                             @RequestParam("search") String search,
                                                             @RequestParam(required = false) String sortedBy,
                                                             @RequestParam(required = false) Boolean isSortedDesc) {
        PageRequest pageable;
        if(sortedBy != null && !sortedBy.isEmpty()) pageable = PageRequest.of(page, number, isSortedDesc != null && isSortedDesc ? Sort.by(sortedBy).descending(): Sort.by(sortedBy));
        else pageable = PageRequest.of(page, number);
        return ResponseEntity.ok(produitRepository.findAllBySearch(pageable, search));
    }

}
