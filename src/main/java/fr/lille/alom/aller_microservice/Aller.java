package fr.lille.alom.aller_microservice;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/") // Gère "/api/test" car "/api" est défini dans @ApplicationPath
public class Aller {

    private final Client client;

    public Aller() {
        this.client = ClientBuilder.newClient(); // Client HTTP Jersey
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String testEndpoint() {
        return "Aller-server is running!";
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO userDTO) {
        // URL du microservice d'authentification
        String authServiceUrl = "http://localhost:8081/authentication-server/api/authenticate";

        // Effectuer une requête POST au microservice d'authentification avec les données non hachées
        Response authResponse = client.target(authServiceUrl)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(userDTO, MediaType.APPLICATION_JSON));

        // Vérifier si l'authentification a réussi
        if (authResponse.getStatus() == 200) {
            // Retourner les informations d'authentification reçues du microservice
            AuthResponseDTO authInfo = authResponse.readEntity(AuthResponseDTO.class);

            // Ajouter le mot de passe non haché dans la réponse si nécessaire
            return Response.ok(new AuthResponseDTO(
                    authInfo.getUsername(),
                    authInfo.getHashedPassword(),
                    authInfo.getToken(),
                    userDTO.getPassword() // Mot de passe fourni dans la requête
            )).build();
        } else {
            // Retourner une réponse 401 en cas d'échec
            return Response.status(Response.Status.UNAUTHORIZED).entity("Authentication failed").build();
        }
    
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
