package LosGuerreros.microservicecommandes.mapper;

import LosGuerreros.microservicecommandes.Model.Commande;
import LosGuerreros.microservicecommandes.dto.CommandeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class CommandeMapper {

    public Page<CommandeDto> mapCommandeToCommandeDto(Page<Commande> commande, Pageable pageable) {
        return new PageImpl<>(commande.stream().map(CommandeDto::new).toList(), pageable, commande.getTotalElements());
    }

}
