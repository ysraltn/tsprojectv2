package tech.ysraltn.tsprojectV2.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsumableDto {
    private Long id;
    private String name;
    private String description;

    // İçerdiği tür isimlerini de DTO içinde dönebiliriz
    private List<ConsumableTypeDto> types;
}
