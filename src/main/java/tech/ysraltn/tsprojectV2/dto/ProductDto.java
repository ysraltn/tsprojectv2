package tech.ysraltn.tsprojectV2.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String serial;
    private String type;
    private String brand;
    private String model;
    private String status;

    private Long institutionId;
    private String institutionName;

    private Long ownerId;
    private String ownerName;

    private Long responsibleId;
    private String responsibleFullName;
}
