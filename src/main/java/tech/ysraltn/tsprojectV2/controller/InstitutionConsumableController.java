package tech.ysraltn.tsprojectV2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ysraltn.tsprojectV2.dto.InstitutionConsumableDto;
import tech.ysraltn.tsprojectV2.service.InstitutionConsumableService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/institution-consumables")
@RequiredArgsConstructor
public class InstitutionConsumableController {

    private final InstitutionConsumableService institutionConsumableService;

    @GetMapping
    public ResponseEntity<List<InstitutionConsumableDto>> getAll() {
        return ResponseEntity.ok(institutionConsumableService.getAll());
    }

    @GetMapping("/bulk")
    public ResponseEntity<Map<Long, List<InstitutionConsumableDto>>> getBulkByInstitutionIds(@RequestParam List<Long> institutionIds) {
        return ResponseEntity.ok(institutionConsumableService.getByInstitutionIds(institutionIds));
    }

    @GetMapping("/by-institution/{institutionId}")
    public ResponseEntity<List<InstitutionConsumableDto>> getByInstitutionId(@PathVariable Long institutionId) {
        return ResponseEntity.ok(institutionConsumableService.getByInstitutionId(institutionId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstitutionConsumableDto> getById(@PathVariable Long id) {
        return institutionConsumableService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InstitutionConsumableDto> create(@RequestBody InstitutionConsumableDto dto) {
        return institutionConsumableService.create(dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (institutionConsumableService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
