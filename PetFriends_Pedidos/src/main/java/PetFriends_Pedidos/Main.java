package PetFriends_Pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "PetFriends_Pedidos")
@EnableJpaRepositories(basePackages = "PetFriends_Pedidos")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println(" Microsserviço PEDIDOS rodando na porta 8080!");
    }
}