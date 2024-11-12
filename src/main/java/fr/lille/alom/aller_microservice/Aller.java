package fr.lille.alom.aller_microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class Aller {

    private final RestTemplate restTemplate;

    public Aller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody UserDTO userDTO) {
        // URL du microservice d'authentification
        String authServiceUrl = "http://localhost:8080/authentication-server/api/authenticate";  // Utilisez le port 8080 pour Authentication

        // Effectuer une requête POST au microservice d'authentification et recevoir un AuthResponseDTO
        ResponseEntity<AuthResponseDTO> response = restTemplate.postForEntity(authServiceUrl, userDTO, AuthResponseDTO.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            // Récupérer la réponse de l'authentification
            AuthResponseDTO authInfo = response.getBody();

            // Retourner les informations d'authentification reçues du microservice d'authentification
            return ResponseEntity.ok(authInfo);
        } else {
            return ResponseEntity.status(401).body(null);  // Retourner une réponse 401 en cas d'échec
        }
    }
}
