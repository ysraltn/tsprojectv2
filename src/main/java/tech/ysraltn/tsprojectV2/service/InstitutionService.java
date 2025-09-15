package tech.ysraltn.tsprojectV2.service;

import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import tech.ysraltn.tsprojectV2.dto.ConsumableTypeDto;
import tech.ysraltn.tsprojectV2.dto.InstitutionDto;
import tech.ysraltn.tsprojectV2.dto.InstitutionRequestDto;
import tech.ysraltn.tsprojectV2.model.ConsumableType;
import tech.ysraltn.tsprojectV2.model.Institution;
import tech.ysraltn.tsprojectV2.model.InstitutionConsumableType;
import tech.ysraltn.tsprojectV2.repository.InstitutionRepository;
import tech.ysraltn.tsprojectV2.repository.specification.InstitutionSpecifications;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstitutionService {

    private final InstitutionRepository institutionRepository;

    public Page<InstitutionDto> getAllInstitutions(String city, String name, Pageable pageable) {
        Specification<Institution> spec = InstitutionSpecifications.withFilters(city, name);
        return institutionRepository.findAll(spec, pageable)
                .map(this::convertToDto);
    }

    public Optional<InstitutionDto> getInstitutionById(Long id) {
        return institutionRepository.findById(id)
                .map(this::convertToDto);
    }

    public InstitutionDto createInstitution(InstitutionRequestDto dto) {
        if (institutionRepository.existsByName(dto.getName())) {
            throw new DataIntegrityViolationException("Bu kurum zaten kayıtlı.");
        }

        Institution institution = Institution.builder()
                .name(dto.getName())
                .city(dto.getCity())
                .build();

        return convertToDto(institutionRepository.save(institution));
    }

    public InstitutionDto updateInstitution(Long id, InstitutionRequestDto dto) {
        Institution institution = institutionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Institution not found"));

        institution.setName(dto.getName());
        institution.setCity(dto.getCity());

        return convertToDto(institutionRepository.save(institution));
    }

    public void deleteInstitution(Long id) {
        institutionRepository.deleteById(id);
    }

    private InstitutionDto convertToDto(Institution institution) {
        List<ConsumableTypeDto> typeDtos = new ArrayList<>();

        if (institution.getConsumableTypes() != null) {
            for (InstitutionConsumableType ict : institution.getConsumableTypes()) {
                ConsumableType ct = ict.getConsumableType();
                typeDtos.add(ConsumableTypeDto.builder()
                        .id(ct.getId())
                        .typeName(ct.getTypeName())
                        .consumableId(ct.getConsumable().getId())
                        .consumableName(ct.getConsumable().getName())
                        .build());
            }
        }

        return InstitutionDto.builder()
                .id(institution.getId())
                .name(institution.getName())
                .city(institution.getCity())
                .consumableTypes(typeDtos)
                .build();
    }
}
