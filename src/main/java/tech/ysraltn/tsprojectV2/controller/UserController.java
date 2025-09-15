package tech.ysraltn.tsprojectV2.controller;

import tech.ysraltn.tsprojectV2.dto.UserDto;
import tech.ysraltn.tsprojectV2.dto.UpdateProfileRequest;
import tech.ysraltn.tsprojectV2.service.UserService;
import tech.ysraltn.tsprojectV2.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getCurrentUserProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        UserDto profile = userService.getUserProfile(userDetails.getId());
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserDto> updateCurrentUserProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody UpdateProfileRequest request) {
        UserDto updatedProfile = userService.updateUserProfile(userDetails.getId(), request);
        return ResponseEntity.ok(updatedProfile);
    }
}
