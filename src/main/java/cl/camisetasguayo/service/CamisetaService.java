package cl.camisetasguayo.service;

import cl.camisetasguayo.dto.CamisetaDTO;
import cl.camisetasguayo.model.Camiseta;
import cl.camisetasguayo.repository.CamisetaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CamisetaService {
    private final CamisetaRepository camisetaRepository;

    public List<CamisetaDTO> findAll() {
        List<Camiseta> camisetas = camisetaRepository.findAll();
        log.info("### Camisetas Encontradas () --> [{}]", camisetas);

        List<CamisetaDTO> camisetaDTOS = camisetas
                .stream()
                .map(camiseta -> CamisetaDTO
                        .builder()
                        .id(camiseta.getId())
                        .anio(camiseta.getAnio())
                        .tipoKit(camiseta.getTipoKit())
                        .urlReferencia(camiseta.getUrlReferencia())
                        .descripcion(camiseta.getDescripcion())
                        .build())
                .collect(Collectors.toList());

        return camisetaDTOS;
    }

    public Optional<CamisetaDTO> findById(Long id) {
        Optional<Camiseta> optionalCamiseta = camisetaRepository.findById(id);
        log.info("### Buscando camisetas por: () --> [{}]", id);

        if(!optionalCamiseta.isPresent()) {
            return Optional.empty();
        }

        CamisetaDTO camisetaDTO = CamisetaDTO
                .builder()
                .id(optionalCamiseta.get().getId())
                .anio(optionalCamiseta.get().getAnio())
                .tipoKit(optionalCamiseta.get().getTipoKit())
                .urlReferencia(optionalCamiseta.get().getUrlReferencia())
                .descripcion(optionalCamiseta.get().getDescripcion())
                .build();
        log.info("### Camiseta Encontrada () --> [{}]", camisetaDTO);

        return Optional.of(camisetaDTO);
    }

    public Optional<CamisetaDTO> update(Long id, CamisetaDTO camisetaDTO) {
        Optional<Camiseta> optionalCamiseta = camisetaRepository.findById(id);

        log.info("### Buscando camisetas por: {} --> [{}]", id, optionalCamiseta);

        if (!optionalCamiseta.isPresent()) {
            return Optional.empty();
        }

        Camiseta camiseta = optionalCamiseta.get();
        camiseta.setAnio(camisetaDTO.getAnio());
        camiseta.setTipoKit(camisetaDTO.getTipoKit());
        camiseta.setUrlReferencia(camisetaDTO.getUrlReferencia());
        camiseta.setDescripcion(camisetaDTO.getDescripcion());
        camiseta.setIdClub(camisetaDTO.getIdClub());

        log.info("### Actualizando camiseta: {} --> [{}]", id, camiseta);

        camisetaRepository.save(camiseta);

        return Optional.of(new CamisetaDTO());
    }

    public boolean save(CamisetaDTO camisetaDTO) {
        log.info("### Guardar Camiseta () --> [{}]", camisetaDTO);

        Camiseta nuevaCamiseta = Camiseta
                .builder()
                .id(camisetaDTO.getId())
                .anio(camisetaDTO.getAnio())
                .tipoKit(camisetaDTO.getTipoKit())
                .urlReferencia(camisetaDTO.getUrlReferencia())
                .descripcion(camisetaDTO.getDescripcion())
                .idClub(camisetaDTO.getIdClub())
                .build();

        log.info("### Producto a Crear () --> [{}]", nuevaCamiseta);
        camisetaRepository.save(nuevaCamiseta);

        boolean creadoExitosamente = nuevaCamiseta.getId() > 0;
        log.info("### Resultado () --> [{}]", creadoExitosamente);

        return creadoExitosamente;
    }
}
