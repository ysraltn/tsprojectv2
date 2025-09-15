package tech.ysraltn.tsprojectV2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.ysraltn.tsprojectV2.dto.InstitutionConsumableDto;
import tech.ysraltn.tsprojectV2.model.Consumable;
import tech.ysraltn.tsprojectV2.model.Institution;
import tech.ysraltn.tsprojectV2.model.InstitutionConsumable;
import tech.ysraltn.tsprojectV2.repository.ConsumableRepository;
import tech.ysraltn.tsprojectV2.repository.InstitutionConsumableRepository;
import tech.ysraltn.tsprojectV2.repository.InstitutionRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstitutionConsumableService {

    private final InstitutionConsumableRepository institutionConsumableRepository;
    private final InstitutionRepository institutionRepository;
    private final ConsumableRepository consumableRepository;

    public List<InstitutionConsumableDto> getAll() {
        return institutionConsumableRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<InstitutionConsumableDto> getById(Long id) {
        return institutionConsumableRepository.findById(id).map(this::convertToDto);
    }

    public Optional<InstitutionConsumableDto> create(InstitutionConsumableDto dto) {
        Optional<Institution> institutionOpt = institutionRepository.findById(dto.getInstitutionId());
        Optional<Consumable> consumableOpt = consumableRepository.findById(dto.getConsumableId());

        if (institutionOpt.isPresent() && consumableOpt.isPresent()) {
            InstitutionConsumable ic = InstitutionConsumable.builder()
                    .institution(institutionOpt.get())
                    .consumable(consumableOpt.get())
                    .build();

            return Optional.of(convertToDto(institutionConsumableRepository.save(ic)));
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
        if (institutionConsumableRepository.existsById(id)) {
            institutionConsumableRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Map<Long, List<InstitutionConsumableDto>> getByInstitutionIds(List<Long> institutionIds) {
        if (institutionIds == null || institutionIds.isEmpty()) {
            return java.util.Collections.emptyMap();
        }
        List<InstitutionConsumable> list = institutionConsumableRepository.findByInstitution_IdIn(institutionIds);
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.groupingBy(InstitutionConsumableDto::getInstitutionId));
    }

    public List<InstitutionConsumableDto> getByInstitutionId(Long institutionId) {
        List<InstitutionConsumable> list = institutionConsumableRepository.findByInstitution_Id(institutionId);
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private InstitutionConsumableDto convertToDto(InstitutionConsumable ic) {
        return InstitutionConsumableDto.builder()
                .id(ic.getId())
                .institutionId(ic.getInstitution().getId())
                .institutionName(ic.getInstitution().getName())
                .consumableId(ic.getConsumable().getId())
                .consumableName(ic.getConsumable().getName())
                .build();
    }
}
