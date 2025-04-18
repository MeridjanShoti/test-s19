package com.example.test_s19.prenotazioni;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrenotazioneRequest {

    private String dataRichiesta;
    @Column(columnDefinition = "TEXT")
    private String noteDipendente;
    private Long dipendente_id;
    @NotBlank(message = "La destinazione non può essere vuota")
    private String destinazione;
    @NotNull(message = "La data non può essere null")
    private LocalDate data;
}
