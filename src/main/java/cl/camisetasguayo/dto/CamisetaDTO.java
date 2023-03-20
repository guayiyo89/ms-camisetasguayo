package cl.camisetasguayo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CamisetaDTO {
    private long id;
    private int anio;
    private long idClub;
    private String tipoKit;
    private String urlReferencia;
    private String descripcion;
}
