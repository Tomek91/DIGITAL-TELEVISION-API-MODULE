package pl.com.app.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TvPackageDTO {
    private Long id;
    private String name;
    private BigDecimal cost;
    private Set<AgreementDurationDTO> agreementDurationsDTO;
}





