package LosGuerreros.APIGateway;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ServiceDiscoveryController {

    private final EurekaClient eurekaClient;

    @Autowired
    public ServiceDiscoveryController(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    @GetMapping("/services")
    public List<String> getAllServices() {
        return eurekaClient.getApplications().getRegisteredApplications().stream()
                .map(application -> application.getName())
                .collect(Collectors.toList());
    }
}
