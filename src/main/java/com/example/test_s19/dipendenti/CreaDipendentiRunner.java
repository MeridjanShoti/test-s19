package com.example.test_s19.dipendenti;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CreaDipendentiRunner implements CommandLineRunner {
    @Autowired
    Faker faker;
    @Autowired
    DipendenteRepository dipendenteRepository;
    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            Dipendente dipendente = new Dipendente();
            dipendente.setNome(faker.name().firstName());
            dipendente.setCognome(faker.name().lastName());
            dipendente.setEmail(dipendente.getNome() + "." + dipendente.getCognome() + "@gmail.com");
            dipendente.setUsername(dipendente.getNome() + "." + dipendente.getCognome());
            dipendente.setAvatar("https://ui-avatars.com/api/?name=" + dipendente.getNome() + "+" + dipendente.getCognome());
            dipendenteRepository.save(dipendente);

        }
    }
}
