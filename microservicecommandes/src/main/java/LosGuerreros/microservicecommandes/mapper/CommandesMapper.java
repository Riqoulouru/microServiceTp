package LosGuerreros.microservicecommandes.mapper;

import LosGuerreros.microservicecommandes.Model.Commandes;
import LosGuerreros.microservicecommandes.dto.CommandesDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandesMapper {

    public Page<CommandesDto> mapCommandesToCommandesDto(Page<Commandes> commandes, Pageable pageable) {
        return new PageImpl<>(commandes.stream().map(CommandesDto::new).toList(), pageable, commandes.getTotalElements());
    }

}
