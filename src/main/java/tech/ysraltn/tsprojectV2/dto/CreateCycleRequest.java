package tech.ysraltn.tsprojectV2.dto;

import jakarta.validation.constraints.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCycleRequest {

    @NotNull(message = "Product ID zorunludur")
    private Long productId;

    @NotNull(message = "Yıl zorunludur")
    @Min(value = 2000, message = "Yıl 2000'den büyük olmalıdır")
    private Integer year;

    @NotNull(message = "Ay zorunludur")
    @Min(value = 1, message = "Ay 1 ile 12 arası olmalıdır")
    @Max(value = 12, message = "Ay 1 ile 12 arası olmalıdır")
    private Integer month;

    @NotNull(message = "Cycle count zorunludur")
    @Min(value = 0, message = "Cycle count negatif olamaz")
    private Integer cycleCount;
}
