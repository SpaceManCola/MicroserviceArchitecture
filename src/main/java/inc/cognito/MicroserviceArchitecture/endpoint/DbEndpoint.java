package inc.cognito.MicroserviceArchitecture.endpoint;

import inc.cognito.MicroserviceArchitecture.model.repository.Client;
import inc.cognito.MicroserviceArchitecture.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("user")
public class DbEndpoint {
    UserRepository userRepository;

    public DbEndpoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("{id}")
    public Client getUser(@PathVariable BigInteger id) {
        return userRepository.getUser(id);
    }
    @PostMapping
    public Client addUser(@RequestBody Client client) {
        return userRepository.addUser(client);

    }
    @PutMapping("{id}")
    public Client updateUser(@PathVariable BigInteger id, @RequestBody Client client) {
        client.setId(id);
        return userRepository.updateUser(client);
    }
    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable BigInteger id) {
        userRepository.deleteUser(id);
        return "OK";
    }
}
