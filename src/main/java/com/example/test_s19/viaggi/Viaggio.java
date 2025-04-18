package com.example.test_s19.viaggi;

import com.example.test_s19.dipendenti.Dipendente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Viaggi")

public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String destinazione;
    private String data;
    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;
}