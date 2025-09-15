package tech.ysraltn.tsprojectV2.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ysraltn.tsprojectV2.dto.InstitutionDto;
import tech.ysraltn.tsprojectV2.dto.InstitutionRequestDto;
import tech.ysraltn.tsprojectV2.service.InstitutionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/institutions")
@RequiredArgsConstructor
public class InstitutionController {

    private final InstitutionService institutionService;

    @GetMapping
    public ResponseEntity<Page<InstitutionDto>> getAllInstitutions(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String name,
            @PageableDefault(size = 10, sort = "name") Pageable pageable
    ) {
        Page<InstitutionDto> institutions = institutionService.getAllInstitutions(city, name, pageable);
        return ResponseEntity.ok(institutions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstitutionDto> getInstitutionById(@PathVariable Long id) {
        Optional<InstitutionDto> dto = institutionService.getInstitutionById(id);
        return dto.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InstitutionDto> createInstitution(@RequestBody InstitutionRequestDto dto) {
        return ResponseEntity.ok(institutionService.createInstitution(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstitutionDto> updateInstitution(@PathVariable Long id,
                                                            @RequestBody InstitutionRequestDto dto) {
        return ResponseEntity.ok(institutionService.updateInstitution(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstitution(@PathVariable Long id) {
        institutionService.deleteInstitution(id);
        return ResponseEntity.noContent().build();
    }
}
