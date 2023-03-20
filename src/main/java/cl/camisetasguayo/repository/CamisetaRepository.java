package cl.camisetasguayo.repository;

import cl.camisetasguayo.model.Camiseta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CamisetaRepository extends JpaRepository<Camiseta, Long> {
    Optional<Camiseta> findByIdClub(Long idClub);
}
