package br.iesb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScrumDeckBackendApplication extends SpringBootApp {

    public static void main(String[] args) {
        onApplicationLoaded(SpringApplication.run(ScrumDeckBackendApplication.class, args));
    }

}
