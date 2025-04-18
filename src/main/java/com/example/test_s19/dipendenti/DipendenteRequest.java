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
    @NotBlank
    private String nome;
    @NotBlank
    private String cognome;
    @NotBlank
    @Column(unique = true)
    private String email;
    @NotBlank
    @Column(unique = true)
    private String username;
}
