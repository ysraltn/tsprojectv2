package tech.ysraltn.tsprojectV2.controller;

import tech.ysraltn.tsprojectV2.dto.CreateCycleRequest;
import tech.ysraltn.tsprojectV2.dto.CycleDto;
import tech.ysraltn.tsprojectV2.model.Cycle;
import tech.ysraltn.tsprojectV2.security.CustomUserDetails;
import tech.ysraltn.tsprojectV2.service.CycleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/cycles")
@RequiredArgsConstructor
public class CycleController {

    private final CycleService cycleService;

    @GetMapping
    public ResponseEntity<List<CycleDto>> getAllCycles() {
        return ResponseEntity.ok(cycleService.getAllCycles());
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<CycleDto>> getCyclesByYear(@PathVariable Integer year) {
        return ResponseEntity.ok(cycleService.getCyclesByYear(year));
    }

    @PostMapping
    public ResponseEntity<CycleDto> createCycle(
            @RequestBody CreateCycleRequest cycleRequest,
            @AuthenticationPrincipal CustomUserDetails userDetails
            //@RequestParam Long userId // Simülasyon amaçlı manuel gönderiyoruz
    ) {
        Long userId = userDetails.getId();
        return ResponseEntity.ok(cycleService.createCycle(userId, cycleRequest));
    }


    @PutMapping("/{id}")
    public ResponseEntity<CycleDto> updateCycle(
            @PathVariable Long id,
            @RequestBody CreateCycleRequest cycleRequest,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getId();
        return ResponseEntity.ok(cycleService.updateCycle(id, userId, cycleRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCycle(@PathVariable Long id) {
        cycleService.deleteCycle(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/user/year/{year}")
    public ResponseEntity<List<CycleDto>> getUserCyclesByYear(
            @PathVariable Integer year,
            @RequestParam Long userId // kullanıcı parametreyle geliyor
    ) {
        return ResponseEntity.ok(cycleService.getCyclesByYearAndUser(year, userId));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<CycleDto>> getFilteredCycles(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long institutionId
    ) {
        return ResponseEntity.ok(
            cycleService.getCyclesWithFilters(year, month, userId, institutionId)
        );
    }


    }

