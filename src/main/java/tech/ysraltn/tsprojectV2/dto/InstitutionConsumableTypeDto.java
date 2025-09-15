package tech.ysraltn.tsprojectV2.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstitutionConsumableTypeDto {
    private Long id;

    private Long institutionId;
    private String institutionName;

    private Long consumableTypeId;
    private String consumableTypeName;
    private Long consumableId;
    private String consumableName;
}
