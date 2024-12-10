package fr.lille.alom.aller_microservice;


import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/") // Chemin de base pour l'API
public class AllerApplication extends ResourceConfig {
    public AllerApplication() {
        packages("fr.lille.alom.aller_microservice"); // Package contenant les classes REST
    }


    public static void main(String[] args) {
        System.out.println("AllerApplication with Jersey is ready.");
    }
}
