package tech.ysraltn.tsprojectV2.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProfileRequest {
    private String name;
    private String surname;
    private String email;
    private String currentPassword;
    private String newPassword;
}
