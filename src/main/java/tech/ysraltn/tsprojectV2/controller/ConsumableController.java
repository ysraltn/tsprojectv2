package tech.ysraltn.tsprojectV2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ysraltn.tsprojectV2.dto.ConsumableDto;
import tech.ysraltn.tsprojectV2.service.ConsumableService;

import java.util.List;

@RestController
@RequestMapping("/api/consumables")
@RequiredArgsConstructor
public class ConsumableController {

    private final ConsumableService consumableService;

    @GetMapping
    public ResponseEntity<List<ConsumableDto>> getAllConsumables() {
        return ResponseEntity.ok(consumableService.getAllConsumables());
    }

    @PostMapping
    public ResponseEntity<ConsumableDto> createConsumable(@RequestBody ConsumableDto dto) {
        return ResponseEntity.ok(consumableService.createConsumable(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsumableDto> getConsumableById(@PathVariable Long id) {
        return consumableService.getConsumableById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsumableDto> updateConsumable(@PathVariable Long id, @RequestBody ConsumableDto dto) {
        return consumableService.updateConsumable(id, dto)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
public ResponseEntity<Void> deleteConsumable(@PathVariable Long id) {
    if (consumableService.deleteConsumable(id)) {
        return ResponseEntity.noContent().build(); // 204 No Content
    } else {
        return ResponseEntity.notFound().build();  // 404 Not Found
    }
}










}
