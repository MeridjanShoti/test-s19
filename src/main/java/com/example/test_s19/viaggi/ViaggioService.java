package com.example.test_s19.viaggi;

import com.example.test_s19.common.CommonResponse;
import com.example.test_s19.dipendenti.Dipendente;
import com.example.test_s19.prenotazioni.Prenotazione;
import com.example.test_s19.prenotazioni.PrenotazioneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@Slf4j
public class ViaggioService {
    @Autowired
    private ViaggioRepository viaggioRepository;
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    public CommonResponse createViaggio(ViaggioRequest viaggioRequest, Prenotazione prenotazione, Dipendente dipendente) {
        Viaggio viaggio = new Viaggio();
        BeanUtils.copyProperties(viaggioRequest, viaggio);
        viaggio.setDipendente(dipendente);
        viaggio = viaggioRepository.save(viaggio);
        prenotazione.setViaggio(viaggio);
        prenotazioneRepository.save(prenotazione);
        return new CommonResponse(viaggio.getId());
    }
    public Viaggio getViaggioById(long id) {
        return viaggioRepository.findById(id).orElseThrow(() -> new RuntimeException("Viaggio non trovato"));
    }
    public Viaggio updateViaggio(long id, ViaggioRequest viaggioRequest) {
        Viaggio viaggio = viaggioRepository.findById(id).orElseThrow(() -> new RuntimeException("Viaggio non trovato"));
        BeanUtils.copyProperties(viaggioRequest, viaggio);
        return viaggioRepository.save(viaggio);
    }
    public void deleteViaggio(long id) {
        if (!viaggioRepository.existsById(id)) {
            throw new RuntimeException("Viaggio non trovato");
        }
        viaggioRepository.deleteById(id);
    }
    public Viaggio updateStato(long id) {
        Viaggio viaggio = viaggioRepository.findById(id).orElseThrow(() -> new RuntimeException("Viaggio non trovato"));
        viaggio.setStato(Stato.TERMINATO);
        return viaggioRepository.save(viaggio);
    }
    public boolean existsById(long id) {
        return viaggioRepository.existsById(id);
    }
    public Page<Viaggio> findAll(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return viaggioRepository.findAll(pageable);
    }
}
