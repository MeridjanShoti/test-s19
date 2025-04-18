package com.example.test_s19.prenotazioni;

import com.example.test_s19.common.CommonResponse;
import com.example.test_s19.dipendenti.Dipendente;
import com.example.test_s19.dipendenti.DipendenteRepository;
import com.example.test_s19.viaggi.ViaggioRepository;
import com.example.test_s19.viaggi.ViaggioRequest;
import com.example.test_s19.viaggi.ViaggioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Service
@Validated
public class PrenotazioneService {
    @Autowired
    PrenotazioneRepository prenotazioneRepository;
    @Autowired
    DipendenteRepository dipendenteRepository;
    @Autowired
    ViaggioService viaggioService;
    @Autowired
    ViaggioRepository viaggioRepository;
    public CommonResponse save(PrenotazioneRequest request, long dipendenteId)
    {
        Dipendente dipendente = dipendenteRepository.findById(dipendenteId).orElseThrow(() -> new RuntimeException("Dipendente non trovato"));
        Prenotazione prenotazione = new Prenotazione();
        BeanUtils.copyProperties(request, prenotazione);
        prenotazione.setDipendente(dipendente);
        ViaggioRequest viaggioRequest = new ViaggioRequest();
        BeanUtils.copyProperties(request, viaggioRequest);
        viaggioService.createViaggio(viaggioRequest, prenotazione, dipendente);
        prenotazione.setViaggio(viaggioService.getViaggioById(prenotazione.getViaggio().getId()));
        if (!prenotazioneRepository.existsByViaggioDataAndDipendenteId(viaggioRequest.getData(), dipendenteId) && viaggioRequest.getData().isAfter(LocalDate.now())) {
            prenotazioneRepository.save(prenotazione);
            viaggioRepository.save(prenotazione.getViaggio());
        } else {
            throw new RuntimeException("Prenotazione non valida");
        }
        return new CommonResponse(prenotazione.getId());

    }
    public Prenotazione getPrenotazioneById(Long id) {
        return prenotazioneRepository.findById(id).orElseThrow(() -> new RuntimeException("Prenotazione non trovata"));
    }
    public void deletePrenotazione(Long id) {
        if (!prenotazioneRepository.existsById(id)) {
            throw new RuntimeException("Prenotazione non trovata");
        }
        prenotazioneRepository.deleteById(id);
    }
    public void updatePrenotazione(Long id, PrenotazioneRequest request) {
        Prenotazione prenotazione = getPrenotazioneById(id);
        BeanUtils.copyProperties(request, prenotazione);
        prenotazioneRepository.save(prenotazione);
    }
    public Page<Prenotazione> findAll(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return prenotazioneRepository.findAll(pageable);
    }
}
