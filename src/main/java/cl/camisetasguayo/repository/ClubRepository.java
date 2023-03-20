package cl.camisetasguayo.repository;


import cl.camisetasguayo.model.Camiseta;
import cl.camisetasguayo.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Camiseta> findByNombreContainingIgnoreCase(String nombre);
}
