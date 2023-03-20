package cl.camisetasguayo.service;

import cl.camisetasguayo.dto.CamisetaDTO;
import cl.camisetasguayo.dto.ClubDTO;
import cl.camisetasguayo.model.Camiseta;
import cl.camisetasguayo.model.Club;
import cl.camisetasguayo.repository.CamisetaRepository;
import cl.camisetasguayo.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;
    private final CamisetaRepository camisetaRepository;

    public List<ClubDTO> findAll() {
        List<Club> clubs = clubRepository.findAll();
        log.info("### Clubes Encontradas () --> [{}]", clubs);

        List<ClubDTO> clubDTOS = clubs
                .stream()
                .map(club -> ClubDTO
                        .builder()
                        .anioFundacion(club.getAnioFundacion())
                        .isNationalTeam(club.isNationalTeam())
                        .nombre(club.getNombre())
                        .estadio(club.getEstadio())
                        .camisetas(club.getCamisetas()
                                .stream()
                                .map(camiseta -> CamisetaDTO
                                        .builder()
                                        .anio(camiseta.getAnio())
                                        .tipoKit(camiseta.getTipoKit())
                                        .urlReferencia(camiseta.getUrlReferencia())
                                        .descripcion(camiseta.getDescripcion())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        return clubDTOS;
    }

    public Optional<ClubDTO> findById(Long id) {
        Optional<Club> optionalClub = clubRepository.findById(id);
        log.info("### Buscando clubes por: () --> [{}]", id);

        if(!optionalClub.isPresent()){
            return Optional.empty();
        }

        ClubDTO clubDTO = ClubDTO
                .builder()
                .anioFundacion(optionalClub.get().getAnioFundacion())
                .isNationalTeam(optionalClub.get().isNationalTeam())
                .nombre(optionalClub.get().getNombre())
                .estadio(optionalClub.get().getEstadio())
                .camisetas(optionalClub.get().getCamisetas()
                        .stream()
                        .map(camiseta -> CamisetaDTO
                                .builder()
                                .anio(camiseta.getAnio())
                                .tipoKit(camiseta.getTipoKit())
                                .urlReferencia(camiseta.getUrlReferencia())
                                .descripcion(camiseta.getDescripcion())
                                .build())
                        .collect(Collectors.toList()))
                .build();

        return Optional.of(clubDTO);
    }

    public boolean save(ClubDTO clubDTO) {
        log.info("### Guardar Club () --> [{}]", clubDTO);

        Club nuevoClub = Club
                .builder()
                .anioFundacion(clubDTO.getAnioFundacion())
                .isNationalTeam(clubDTO.isNationalTeam())
                .nombre(clubDTO.getNombre())
                .estadio(clubDTO.getEstadio())
                .build();

        clubRepository.save(nuevoClub);

        boolean creadoExitosamente = nuevoClub.getId() > 0;

        if(creadoExitosamente) {
            clubDTO.getCamisetas().forEach(camisetaDTO -> {
                Camiseta nuevaCamiseta = Camiseta
                        .builder()
                        .idClub(nuevoClub.getId())
                        .anio(camisetaDTO.getAnio())
                        .tipoKit(camisetaDTO.getTipoKit())
                        .urlReferencia(camisetaDTO.getUrlReferencia())
                        .descripcion(camisetaDTO.getDescripcion())
                        .build();
                camisetaRepository.save(nuevaCamiseta);
            });
        }

        return creadoExitosamente;
    }

}
