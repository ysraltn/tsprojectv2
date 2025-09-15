package tech.ysraltn.tsprojectV2.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CycleDto {
    private Long id;

    // Cycle bilgileri
    private Integer year;
    private Integer month;
    private Integer cycleCount;

    // Ürün bilgileri
    private Long productId;
    private String productSerial;
    private String productType;
    private String productBrand;
    private String productModel;
    private String productStatus;

    // Kurum bilgisi
    private Long institutionId;
    private String institutionName;
    private String institutionCity;

    // Sahip bilgisi
    private Long ownerId;
    private String ownerName;

    // Sorumlu kullanıcı
    private Long responsibleId;
    private String responsibleFullName;
}
