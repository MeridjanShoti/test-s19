package com.example.test_s19.dipendenti;

import com.example.test_s19.common.CommonResponse;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {
    @Autowired
    private DipendenteService dipendenteService;
    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Page<Dipendente> findAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sortBy) {

        return dipendenteService.findAll(page, size, sortBy);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Dipendente findById(@RequestParam Long id) {
        return dipendenteService.getDipendenteById(id);
    }
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse createDipendente(@RequestBody DipendenteRequest request) throws MessagingException {
        return dipendenteService.createDipendente(request);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Dipendente updateDipendente(@PathVariable Long id, @RequestBody DipendenteRequest request) {
        return dipendenteService.updateDipendente(id, request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDipendente(@PathVariable Long id) {
        dipendenteService.deleteDipendente(id);
    }
    @PatchMapping(value = "/{id}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)

    public Dipendente uploadImage(@PathVariable Long id, @RequestPart MultipartFile file) {
        dipendenteService.uploadImage(id, file);
        return dipendenteService.getDipendenteById(id);
    }
}