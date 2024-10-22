package fr.lille.alom.authentication_server;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        // Ici, on pourrait ajouter la logique pour valider les informations
        if ("admin".equals(username) && "password".equals(password)) {
            return "Login successful!";
        }
        return "Login failed!";
    }
}
