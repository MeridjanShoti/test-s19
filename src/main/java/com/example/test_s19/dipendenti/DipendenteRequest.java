package com.example.test_s19.dipendenti;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DipendenteRequest {
    @NotBlank (message = "Il nome non può essere vuoto")
    private String nome;
    @NotBlank (message = "Il cognome non può essere vuoto")
    private String cognome;
    @NotBlank (message = "L'email non può essere vuota")
    @Column(unique = true)
    private String email;
    @NotBlank (message = "L'username non può essere vuoto")
    @Column(unique = true)
    private String username;

}
