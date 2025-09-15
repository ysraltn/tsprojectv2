package tech.ysraltn.tsprojectV2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ysraltn.tsprojectV2.dto.InstitutionConsumableTypeDto;
import tech.ysraltn.tsprojectV2.service.InstitutionConsumableTypeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/institution-consumable-types")
@RequiredArgsConstructor
public class InstitutionConsumableTypeController {

    private final InstitutionConsumableTypeService service;

    @GetMapping
    public ResponseEntity<List<InstitutionConsumableTypeDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/by-institution/{institutionId}")
    public ResponseEntity<List<InstitutionConsumableTypeDto>> getByInstitution(@PathVariable Long institutionId) {
        return ResponseEntity.ok(service.getByInstitutionId(institutionId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstitutionConsumableTypeDto> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InstitutionConsumableTypeDto> create(@RequestParam Long institutionId,
                                                                @RequestParam Long consumableTypeId) {
        Optional<InstitutionConsumableTypeDto> result = service.create(institutionId, consumableTypeId);
        return result.map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}
