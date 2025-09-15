package tech.ysraltn.tsprojectV2.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstitutionConsumableDto {
    private Long id;
    private Long institutionId;
    private String institutionName;
    private Long consumableId;
    private String consumableName;
}
