package cl.camisetasguayo.dto;

import cl.camisetasguayo.model.Camiseta;
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
public class ClubDTO {
    private int anioFundacion;
    private boolean isNationalTeam;
    private String nombre;
    private String estadio;
    private List<CamisetaDTO> camisetas;
}
