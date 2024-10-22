package fr.lille.alom.authentication_server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class Aller {

    @Autowired
    private Authentication authenticationService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        // Get the message from the Authentication service
        String message = authenticationService.authenticate(userDTO.getUsername(), userDTO.getPassword());

        // If the message is "Login successful", return OK (200), otherwise return 401 (Unauthorized)
        if ("Login successful".equals(message)) {
            return ResponseEntity.ok(message);  // Return 200 OK with success message
        } else {
            return ResponseEntity.status(401).body(message);  // Return 401 Unauthorized with failure message
        }
    }
}

