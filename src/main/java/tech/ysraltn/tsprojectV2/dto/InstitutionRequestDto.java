package tech.ysraltn.tsprojectV2.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstitutionRequestDto {
    private String name;
    private String city;
}
