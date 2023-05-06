package LosGuerreros.microserviceclients.Controller;

import LosGuerreros.microserviceclients.Model.*;
import LosGuerreros.microserviceclients.Repository.CartCompositionRepository;
import LosGuerreros.microserviceclients.Repository.ClientRepository;
import LosGuerreros.microserviceclients.Repository.WishCompositionRepository;
import LosGuerreros.microserviceclients.dto.AuthRequest;
import LosGuerreros.microserviceclients.dto.ClientDTO;
import LosGuerreros.microserviceclients.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/clients")
public class ClientsController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private WishCompositionRepository wishCompositionRepository;
    @Autowired
    private CartCompositionRepository cartCompositionRepository;
    @Autowired
    private AuthService authService;

    @GetMapping("/all")
    public ResponseEntity<Page<ClientDTO>> getAllClient(@RequestParam("page") int page, @RequestParam("number") int number) {
        Pageable pageable = PageRequest.of(page, number);
        Page<Client> client = clientRepository.findAll(pageable);
        return ResponseEntity.ok(new PageImpl<>(client.map(ClientDTO::new).toList(), pageable, client.getTotalElements() ));
    }

    @GetMapping("/one/{login}")
    public ResponseEntity<ClientDTO> getAllClient(@PathVariable("login") String login) {
        return ResponseEntity.ok(new ClientDTO(clientRepository.findByLogin(login).orElse(null)));
    }

    @PostMapping("/save")
    public @ResponseBody ResponseEntity<ClientDTO> getAllClient(@RequestBody Client client) {
        return ResponseEntity.ok(new ClientDTO(clientRepository.save(client)));
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

    @DeleteMapping("/remove/wish/{login}/{idProduit}")
    public ResponseEntity<String> removeProductToWishList(@PathVariable("login") String login, @PathVariable("idProduit") Integer idProduit) {
        Client client = clientRepository.findByLogin(login).orElse(null);
        if(client == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).header("Error", "User not found").build();
        wishCompositionRepository.deleteByLoginAndIdProduit(login, idProduit);
        return ResponseEntity.ok("Product remove to the wishlist");
    }

    @DeleteMapping("/remove/cart/{login}/{idProduit}")
    public ResponseEntity<String> removeProductToCart(@PathVariable("login") String login, @PathVariable("idProduit") Integer idProduit) {
        Client client = clientRepository.findByLogin(login).orElse(null);
        if(client == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).header("Error", "User not found").build();
        cartCompositionRepository.deleteByLoginAndIdProduit(login, idProduit);
        return ResponseEntity.ok("Product remove to the cart");
    }

    @DeleteMapping("/reset/cart/{login}")
    public ResponseEntity<String> removeProductToCart(@PathVariable("login") String login) {
        Client client = clientRepository.findByLogin(login).orElse(null);
        if(client == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).header("Error", "User not found").build();
        cartCompositionRepository.deleteByLogin(login);
        return ResponseEntity.ok("Product remove to the cart");
    }

}
