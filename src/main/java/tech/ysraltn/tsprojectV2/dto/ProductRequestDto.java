package tech.ysraltn.tsprojectV2.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDto {
    private String serial;
    private String type;
    private String brand;
    private String model;
    private String status;

    private Long institutionId;
    private Long ownerId;
    private Long responsibleId;
}
