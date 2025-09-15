package tech.ysraltn.tsprojectV2.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProductAssignmentDto {
    private Long id;
    private Long userId;
    private String username;
    private Long productId;
    private String productSerial;
}
