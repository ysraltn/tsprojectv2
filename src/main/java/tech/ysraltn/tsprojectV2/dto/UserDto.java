package tech.ysraltn.tsprojectV2.dto;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password; // Şifre alanı eklendi
    private Set<String> roleNames; // Çoklu rol desteği
}
