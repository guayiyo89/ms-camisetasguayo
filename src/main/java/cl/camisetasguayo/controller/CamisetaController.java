package cl.camisetasguayo.controller;

import cl.camisetasguayo.dto.CamisetaDTO;
import cl.camisetasguayo.service.CamisetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/camisetas")
@RequiredArgsConstructor

public class CamisetaController {
    private final CamisetaService camisetaService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CamisetaDTO> findAll() {
        return camisetaService.findAll();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CamisetaDTO> findById(@PathVariable Long id) {
        Optional<CamisetaDTO> optionalCamiseta = camisetaService.findById(id);
        return optionalCamiseta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@RequestBody CamisetaDTO camisetaDTO) {
        boolean fueCreado = camisetaService.save(camisetaDTO);

        HttpStatus resultado = fueCreado ? HttpStatus.ACCEPTED : HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(resultado).build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> save(@RequestBody CamisetaDTO camisetaDTO) {
        boolean fueCreado = camisetaService.save(camisetaDTO);

        HttpStatus resultado = fueCreado ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(resultado).build();
    }


}
