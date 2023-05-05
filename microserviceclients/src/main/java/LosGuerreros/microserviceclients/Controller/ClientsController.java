package LosGuerreros.microserviceclients.Controller;

import LosGuerreros.microserviceclients.Model.*;
import LosGuerreros.microserviceclients.Repository.ClientRepository;
import LosGuerreros.microserviceclients.dto.AuthRequest;
import LosGuerreros.microserviceclients.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/clients")
public class ClientsController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AuthService authService;

    @GetMapping("/all")
    public Page<Client> getAllClient(@RequestParam("page") int page, @RequestParam("number") int number) {
        return clientRepository.findAll(PageRequest.of(page, number));
    }

    @PostMapping("/save")
    public @ResponseBody Client getAllClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @PostMapping("/register")
    public @ResponseBody ResponseEntity<String> addNewUser(@RequestBody Client user) {
        return ResponseEntity.ok(authService.saveClient(user));
    }

    @PostMapping("/token")
    public @ResponseBody ResponseEntity<String> getToken(@RequestBody AuthRequest authRequest) {
        try {
            if (this.authService.existingClientByLogin(authRequest.getUsername(), authRequest.getPassword())) {
                Client client = clientRepository.findByLogin(authRequest.getUsername()).orElse(null);
                return ResponseEntity.ok(this.authService.generateToken(client));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("This login doesn't match with any clients.");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("This client doesn't exist.");
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        authService.validateToken(token);
        return "Token is valid";
    }

    @PatchMapping("/update/wish/{login}/{idProduit}")
    public ResponseEntity<String> addProductToTheWishList(@PathVariable("login") String login, @PathVariable("idProduit") Integer idProduit) {
        Client client = clientRepository.findByLogin(login).orElse(null);
        if(client == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).header("Error", "User not found").build();
        WishComposition compo = new WishComposition();
        compo.setClient(client);
        compo.setWishCompositionId(new WishCompositionId(client.getLogin(), idProduit));
        client.getWishCompositions().add(compo);
        try { clientRepository.save(client); }
        catch (Exception e) { return ResponseEntity.status(HttpStatus.CONFLICT).body("Combinaison already exist"); }
        return ResponseEntity.ok("Product added to the wish list");
    }

    @PatchMapping("/update/cart/{login}/{idProduit}")
    public ResponseEntity<String> addProductToTheCart(@PathVariable("login") String login, @PathVariable("idProduit") Integer idProduit) {
        Client client = clientRepository.findByLogin(login).orElse(null);
        if(client == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).header("Error", "User not found").build();
        CartComposition compo = new CartComposition();
        compo.setClient(client);
        compo.setCartCompositionId(new CartCompositionId(client.getLogin(), idProduit));
        client.getCartCompositions().add(compo);
        try { clientRepository.save(client); }
        catch (Exception e) { return ResponseEntity.status(HttpStatus.CONFLICT).body("Combinaison already exist"); }
        return ResponseEntity.ok("Product added to the cart");
    }

}
