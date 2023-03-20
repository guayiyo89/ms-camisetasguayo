package cl.camisetasguayo.controller;

import cl.camisetasguayo.dto.ClubDTO;
import cl.camisetasguayo.service.ClubService;
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
public class ClubController {
    private final ClubService clubService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClubDTO> findAll() {
        return clubService.findAll();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClubDTO> findById(@PathVariable Long id) {
        Optional<ClubDTO> optionalClub = clubService.findById(id);
        return optionalClub.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> save(@RequestBody ClubDTO clubDTO) {
        boolean fueCreado = clubService.save(clubDTO);

        HttpStatus resultado = fueCreado ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(resultado).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@RequestBody ClubDTO clubDTO) {
        boolean fueCreado = clubService.save(clubDTO);

        HttpStatus resultado = fueCreado ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(resultado).build();
    }
}
