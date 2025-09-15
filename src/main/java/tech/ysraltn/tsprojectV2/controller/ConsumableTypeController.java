package tech.ysraltn.tsprojectV2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ysraltn.tsprojectV2.dto.ConsumableTypeDto;
import tech.ysraltn.tsprojectV2.service.ConsumableTypeService;

import java.util.List;

@RestController
@RequestMapping("/api/consumable-types")
@RequiredArgsConstructor
public class ConsumableTypeController {

    private final ConsumableTypeService consumableTypeService;

    @GetMapping
    public ResponseEntity<List<ConsumableTypeDto>> getAll() {
        return ResponseEntity.ok(consumableTypeService.getAllTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsumableTypeDto> getById(@PathVariable Long id) {
        return consumableTypeService.getTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-consumable/{consumableId}")
    public ResponseEntity<List<ConsumableTypeDto>> getByConsumableId(@PathVariable Long consumableId) {
        return ResponseEntity.ok(consumableTypeService.getTypesByConsumableId(consumableId));
    }

    @PostMapping
    public ResponseEntity<ConsumableTypeDto> create(@RequestBody ConsumableTypeDto dto) {
        return consumableTypeService.createType(dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = consumableTypeService.deleteType(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}


