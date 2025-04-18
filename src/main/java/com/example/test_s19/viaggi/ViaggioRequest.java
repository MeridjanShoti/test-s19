package com.example.test_s19.viaggi;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ViaggioRequest {
    @NotBlank
    private String destinazione;
    @NotNull
    private int durata;
    private LocalDate data;
}
