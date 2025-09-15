package tech.ysraltn.tsprojectV2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import tech.ysraltn.tsprojectV2.dto.CycleDto;
import tech.ysraltn.tsprojectV2.dto.UserProductAssignmentDto;
import tech.ysraltn.tsprojectV2.security.CustomUserDetails;
import tech.ysraltn.tsprojectV2.service.UserProductAssignmentService;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class UserProductAssignmentController {

    private final UserProductAssignmentService assignmentService;

    @PostMapping("/assign")
    public ResponseEntity<Void> assignProduct(@RequestParam Long userId, @RequestParam Long productId) {
        assignmentService.assignProductToUser(userId, productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/unassign")
    public ResponseEntity<Void> unassignProduct(@RequestParam Long userId, @RequestParam Long productId) {
        assignmentService.unassignProductFromUser(userId, productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserProductAssignmentDto>> getAssignmentsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByUserId(userId));
    }

    @GetMapping("/user/assignments")
    public ResponseEntity<List<CycleDto>> getMyAssignments(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getId();
        return ResponseEntity.ok(assignmentService.getCycleDtosForUser(userId));
    }

    @GetMapping("/user/products")
    public ResponseEntity<List<UserProductAssignmentDto>> getMyAssignedProducts(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getId();
        return ResponseEntity.ok(assignmentService.getAssignmentsByUserId(userId));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<UserProductAssignmentDto>> getAssignmentsByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByProductId(productId));   
    }



}
