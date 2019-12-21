package pl.com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Channel2DTO {
    private Long id;
    private String name;
    private TvPackageDTO tvPackageDTO;
    private CategoryDTO categoryDTO;
}
