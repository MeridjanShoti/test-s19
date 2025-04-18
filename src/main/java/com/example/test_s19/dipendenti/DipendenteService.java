package com.example.test_s19.dipendenti;

import com.example.test_s19.cloudinary.CloudinaryService;
import com.example.test_s19.common.CommonResponse;
import com.example.test_s19.common.EmailSenderService;
import com.example.test_s19.exceptions.NotFoundException;
import com.example.test_s19.exceptions.UsernameException;
import jakarta.mail.MessagingException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

@Service
@Validated
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private EmailSenderService emailSenderService;
    public CommonResponse createDipendente(DipendenteRequest request) throws MessagingException {
        Dipendente dipendente = new Dipendente();
        BeanUtils.copyProperties(request, dipendente);
        if (dipendenteRepository.existsByUsername(dipendente.getUsername())) {
            throw new UsernameException("Username già in uso");
        }
        if (dipendenteRepository.existsByEmail(dipendente.getEmail())) {
            throw new UsernameException("Email già in uso");
        }
        dipendente = dipendenteRepository.save(dipendente);
        emailSenderService.sendEmail(dipendente.getEmail(), "Benvenuto", "Ciao "+dipendente.getNome() + " " + dipendente.getCognome()+"! Benvenuto nella nostra azienda!" );
        return new CommonResponse(dipendente.getId());
    }
    public Page<Dipendente> findAll(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return dipendenteRepository.findAll(pageable);
    }

    public Dipendente getDipendenteById(Long id) {
        return dipendenteRepository.findById(id).orElseThrow(() -> new NotFoundException("Dipendente non trovato"));
    }
    public Dipendente updateDipendente(Long id, DipendenteRequest request) {
        Dipendente dipendente = dipendenteRepository.findById(id).orElseThrow(() -> new NotFoundException("Dipendente non trovato"));
        BeanUtils.copyProperties(request, dipendente);
        return dipendenteRepository.save(dipendente);
    }
    public void deleteDipendente(Long id) {
        if (!dipendenteRepository.existsById(id)) {
            throw new NotFoundException("Dipendente non trovato");
        }
        dipendenteRepository.deleteById(id);
    }
    public boolean existsByUsername(String username) {
        return dipendenteRepository.existsByUsername(username);
    }
    public void uploadImage(Long id, MultipartFile file) {
        Dipendente dipendente = dipendenteRepository.findById(id).orElseThrow(() -> new NotFoundException("Dipendente non trovato"));
        dipendente.setAvatar(cloudinaryService.uploadImage(file));
        dipendenteRepository.save(dipendente);
    }
}
