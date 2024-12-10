package fr.lille.alom.aller_microservice;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/login")
public class Aller {

    private final Client client;

    public Aller() {
        this.client = ClientBuilder.newClient(); // Client HTTP Jersey
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO userDTO) {
        // URL du microservice user-service pour obtenir la liste des utilisateurs
        String userServiceUrl = "http://localhost:8082/user-service/api/user/list";

        // Effectuer une requête GET au microservice user-service pour obtenir la liste des utilisateurs
        Response userServiceResponse = client.target(userServiceUrl)
                .request(MediaType.APPLICATION_JSON)
                .get();

        // Vérifier si la requête a réussi
        if (userServiceResponse.getStatus() != 200) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur lors de la récupération de la liste des utilisateurs.")
                    .build();
        }

        // Lire la liste des utilisateurs
        List<UserDTO> userList = userServiceResponse.readEntity(new javax.ws.rs.core.GenericType<List<UserDTO>>() {});

        // Parcourir la liste pour vérifier les identifiants
        for (UserDTO user : userList) {
            if (user.getUsername().equals(userDTO.getUsername())
                    && user.getPassword().equals(userDTO.getPassword())) {
                // Si les identifiants correspondent, générer un jeton
                String token = "token_" + user.getUsername(); // Exemple simple de génération de jeton
                return Response.ok(new AuthResponseDTO(user.getUsername(), "hashedPassword", token)).build();
            }
        }

        // Si aucun utilisateur ne correspond, retourner une réponse 401 (non autorisé)
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity("Identifiants invalides.")
                .build();
    }
}


/* package fr.lille.alom.aller_microservice;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
public class Aller {

    private final Client client;

    public Aller() {
        this.client = ClientBuilder.newClient(); // Client HTTP Jersey
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO userDTO) {
        // URL du microservice d'authentification
        String authServiceUrl = "http://localhost:8081/authentication-server/api/authenticate";

        // Effectuer une requête POST au microservice d'authentification
        Response response = client.target(authServiceUrl)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(userDTO, MediaType.APPLICATION_JSON));

        // Vérifier si l'authentification a réussi
        if (response.getStatus() == 200) {
            // Retourner les informations d'authentification reçues du microservice
            AuthResponseDTO authInfo = response.readEntity(AuthResponseDTO.class);
            return Response.ok(authInfo).build();
        } else {
            // Retourner une réponse 401 en cas d'échec
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
} */
