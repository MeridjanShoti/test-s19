package com.example.test_s19.dipendenti;

import com.example.test_s19.common.CommonResponse;
import com.example.test_s19.exceptions.NotFoundException;
import com.example.test_s19.exceptions.UsernameException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;
    public CommonResponse createDipendente(DipendenteRequest request) {
        Dipendente dipendente = new Dipendente();
        BeanUtils.copyProperties(request, dipendente);
        if (dipendenteRepository.existsByUsername(dipendente.getUsername())) {
            throw new UsernameException("Username già in uso");
        }
        if (dipendenteRepository.existsByEmail(dipendente.getEmail())) {
            throw new UsernameException("Email già in uso");
        }
        dipendente = dipendenteRepository.save(dipendente);
        return new CommonResponse(dipendente.getId());
    }
    public Page<Dipendente> findAll(Pageable pageable) {
        return dipendenteRepository.findAll(pageable);
    }

    public Dipendente getDipendenteById(Long id) {
        return dipendenteRepository.findById(id).orElseThrow(() -> new NotFoundException("Dipendente non trovato"));
    }
    public Dipendente updateDipendente(Long id, Dipendente dipendente) {
        Dipendente existingDipendente = dipendenteRepository.findById(id).orElseThrow(() -> new NotFoundException("Dipendente non trovato"));
        if (existingDipendente != null) {
            existingDipendente.setNome(dipendente.getNome());
            existingDipendente.setCognome(dipendente.getCognome());
            existingDipendente.setEmail(dipendente.getEmail());
            existingDipendente.setUsername(dipendente.getUsername());
            return dipendenteRepository.save(existingDipendente);
        }
        return null;
    }
    public void deleteDipendente(Long id) {
        dipendenteRepository.deleteById(id);
    }
    public boolean existsByUsername(String username) {
        return dipendenteRepository.existsByUsername(username);
    }
}
