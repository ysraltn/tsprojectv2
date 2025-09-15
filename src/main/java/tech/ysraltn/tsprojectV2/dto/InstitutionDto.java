package tech.ysraltn.tsprojectV2.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstitutionDto {
    private Long id;
    private String name;
    private String city;
    private List<ConsumableTypeDto> consumableTypes;
}
