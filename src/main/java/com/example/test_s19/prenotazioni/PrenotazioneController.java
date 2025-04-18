package com.example.test_s19.prenotazioni;



import com.example.test_s19.common.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Page<Prenotazione> findAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sortBy) {

        return prenotazioneService.findAll(page, size, sortBy);
    }
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Prenotazione getPrenotazioneById(@PathVariable Long id) {
        return prenotazioneService.getPrenotazioneById(id);
    }
    @PostMapping("/utente/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse createPrenotazione(@RequestBody PrenotazioneRequest request, @PathVariable Long id) {
       return prenotazioneService.save(request, id);
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrenotazione(@PathVariable Long id) {
        prenotazioneService.deletePrenotazione(id);
    }
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePrenotazione(@PathVariable Long id, @RequestBody PrenotazioneRequest request) {
        prenotazioneService.updatePrenotazione(id, request);
    }

}
