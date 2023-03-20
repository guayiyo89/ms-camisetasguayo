package cl.camisetasguayo.dto;

import cl.camisetasguayo.model.Camiseta;
import cl.camisetasguayo.model.Club;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaisDTO {
    private long id;
    private String nombre;
    private String confederacion;
    private List<ClubDTO> clubes;
}
