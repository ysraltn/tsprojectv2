package tech.ysraltn.tsprojectV2.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsumableTypeDto {
    private Long id;
    private String typeName;
    private Long consumableId;
    private String consumableName;
}

