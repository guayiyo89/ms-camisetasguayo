package cl.camisetasguayo.controller;

import cl.camisetasguayo.dto.CamisetaDTO;
import cl.camisetasguayo.dto.PaisDTO;
import cl.camisetasguayo.service.PaisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pais")
@RequiredArgsConstructor
public class PaisController {

    private final PaisService paisService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PaisDTO> findAll() {
        return paisService.findAll();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaisDTO> findById(@PathVariable Long id) {
        Optional<PaisDTO> optionalPais = paisService.findById(id);
        return optionalPais.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@RequestBody PaisDTO paisDTO) {
        boolean fueCreado = paisService.save(paisDTO);

        HttpStatus resultado = fueCreado ? HttpStatus.ACCEPTED : HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(resultado).build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> save(@RequestBody PaisDTO paisDTO) {
        boolean fueCreado = paisService.save(paisDTO);

        HttpStatus resultado = fueCreado ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(resultado).build();
    }
}
