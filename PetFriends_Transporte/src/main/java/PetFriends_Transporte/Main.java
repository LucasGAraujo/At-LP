package PetFriends_Transporte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println(" Microsserviço TRANSPORTE rodando na porta 8082!");
    }
}