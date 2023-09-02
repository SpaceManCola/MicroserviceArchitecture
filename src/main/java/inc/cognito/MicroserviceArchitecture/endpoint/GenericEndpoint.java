package inc.cognito.MicroserviceArchitecture.endpoint;

import inc.cognito.MicroserviceArchitecture.model.api.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class GenericEndpoint {

    @GetMapping("health")
    public ResponseEntity<Response> health() {
        return ResponseEntity.ok(new Response("OK"));
    }
}
