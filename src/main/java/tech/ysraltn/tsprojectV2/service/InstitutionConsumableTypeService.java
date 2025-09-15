package tech.ysraltn.tsprojectV2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.ysraltn.tsprojectV2.dto.InstitutionConsumableTypeDto;
import tech.ysraltn.tsprojectV2.model.ConsumableType;
import tech.ysraltn.tsprojectV2.model.Institution;
import tech.ysraltn.tsprojectV2.model.InstitutionConsumableType;
import tech.ysraltn.tsprojectV2.repository.ConsumableTypeRepository;
import tech.ysraltn.tsprojectV2.repository.InstitutionConsumableTypeRepository;
import tech.ysraltn.tsprojectV2.repository.InstitutionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstitutionConsumableTypeService {

    private final InstitutionConsumableTypeRepository institutionConsumableTypeRepository;
    private final InstitutionRepository institutionRepository;
    private final ConsumableTypeRepository consumableTypeRepository;

    public List<InstitutionConsumableTypeDto> getAll() {
        return institutionConsumableTypeRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public Optional<InstitutionConsumableTypeDto> getById(Long id) {
        return institutionConsumableTypeRepository.findById(id)
                .map(this::convertToDto);
    }

    public Optional<InstitutionConsumableTypeDto> create(Long institutionId, Long consumableTypeId) {
        Optional<Institution> institutionOpt = institutionRepository.findById(institutionId);
        Optional<ConsumableType> typeOpt = consumableTypeRepository.findById(consumableTypeId);

        if (institutionOpt.isEmpty() || typeOpt.isEmpty()) {
            return Optional.empty();
        }

        InstitutionConsumableType entity = InstitutionConsumableType.builder()
                .institution(institutionOpt.get())
                .consumableType(typeOpt.get())
                .build();

        InstitutionConsumableType saved = institutionConsumableTypeRepository.save(entity);
        return Optional.of(convertToDto(saved));
    }

    public List<InstitutionConsumableTypeDto> getByInstitutionId(Long institutionId) {
        return institutionConsumableTypeRepository.findByInstitutionId(institutionId)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    private InstitutionConsumableTypeDto convertToDto(InstitutionConsumableType entity) {
        return InstitutionConsumableTypeDto.builder()
                .id(entity.getId())
                .institutionId(entity.getInstitution().getId())
                .institutionName(entity.getInstitution().getName())
                .consumableTypeId(entity.getConsumableType().getId())
                .consumableTypeName(entity.getConsumableType().getTypeName())
                .consumableId(entity.getConsumableType().getConsumable().getId())
                .consumableName(entity.getConsumableType().getConsumable().getName())
                .build();
    }
}
