package cl.camisetasguayo.service;

import cl.camisetasguayo.dto.ClubDTO;
import cl.camisetasguayo.dto.PaisDTO;
import cl.camisetasguayo.model.Club;
import cl.camisetasguayo.model.Pais;
import cl.camisetasguayo.repository.CamisetaRepository;
import cl.camisetasguayo.repository.ClubRepository;
import cl.camisetasguayo.repository.PaisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaisService {
    private final CamisetaRepository camisetaRepository;
    private final ClubRepository clubRepository;
    private final PaisRepository paisRepository;

    public List<PaisDTO> findAll() {
        List<Pais> paises = paisRepository.findAll();
        log.info("### Paises Encontradas () --> [{}]", paises);

        List<PaisDTO> paisDTOS = paises
                .stream()
                .map(pais -> PaisDTO
                        .builder()
                        .id(pais.getId())
                        .nombre(pais.getNombre())
                        .confederacion(pais.getConfederacion())
                        .build())
                .collect(Collectors.toList());

        return paisDTOS;
    }

    public Optional<PaisDTO> findById(Long id) {
        Optional<Pais> optionalPais = paisRepository.findById(id);
        log.info("### Buscando paises por: () --> [{}]", id);

        if(!optionalPais.isPresent()){
            return Optional.empty();
        }

        PaisDTO paisDTO = PaisDTO
                .builder()
                .id(optionalPais.get().getId())
                .nombre(optionalPais.get().getNombre())
                .confederacion(optionalPais.get().getConfederacion())
                .clubes(optionalPais.get().getClubes()
                        .stream()
                        .map(club -> ClubDTO
                                .builder()
                                .anioFundacion(club.getAnioFundacion())
                                .isNationalTeam(club.isNationalTeam())
                                .nombre(club.getNombre())
                                .estadio(club.getEstadio())
                                .build())
                        .collect(Collectors.toList()))
                .build();

        return Optional.of(paisDTO);
    }

    public boolean save(PaisDTO paisDTO) {
        log.info("### Guardar Pais () --> [{}]", paisDTO);

        Pais nuevoPais = Pais
                .builder()
                .nombre(paisDTO.getNombre())
                .confederacion(paisDTO.getConfederacion())
                .build();

        paisRepository.save(nuevoPais);

        boolean creadoExitosamente = nuevoPais.getId() > 0;

        if(creadoExitosamente) {
            paisDTO.getClubes().forEach(clubDTO -> {
                Club nuevoClub = Club
                        .builder()
                        .idPais(nuevoPais.getId())
                        .anioFundacion(clubDTO.getAnioFundacion())
                        .isNationalTeam(clubDTO.isNationalTeam())
                        .nombre(clubDTO.getNombre())
                        .estadio(clubDTO.getEstadio())
                        .build();
                clubRepository.save(nuevoClub);
            });
        }

        return creadoExitosamente;
    }
}
