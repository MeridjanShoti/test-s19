package com.example.test_s19.prenotazioni;


import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    boolean existsByViaggioDataAndDipendenteId(LocalDate data, Long dipendenteId);
}