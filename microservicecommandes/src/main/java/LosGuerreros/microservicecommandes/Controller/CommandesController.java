package LosGuerreros.microservicecommandes.Controller;


import LosGuerreros.microservicecommandes.Model.Commandes;
import LosGuerreros.microservicecommandes.Repository.CommandesRepository;
import LosGuerreros.microservicecommandes.dto.CommandesDto;
import LosGuerreros.microservicecommandes.mapper.CommandesMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/commandes")
public class CommandesController {

    @Autowired
    private CommandesRepository commandesRepository;

    @Resource
    CommandesMapper commandesMapper;

    @GetMapping("/all")
    public Page<CommandesDto> getAllClient(@RequestParam("page") int page, @RequestParam("number") int number) {
        Pageable pageable = PageRequest.of(page, number);
        return commandesMapper.mapCommandesToCommandesDto(commandesRepository.findAll(pageable), pageable);
    }

}
