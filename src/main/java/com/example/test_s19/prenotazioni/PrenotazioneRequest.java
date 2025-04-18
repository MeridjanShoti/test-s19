package com.example.test_s19.prenotazioni;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrenotazioneRequest {

    private String dataRichiesta;
    @Column(columnDefinition = "TEXT")
    private String noteDipendente;
    private Long dipendente_id;
    private Long viaggio_id;
}
