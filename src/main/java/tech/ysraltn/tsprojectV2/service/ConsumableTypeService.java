package tech.ysraltn.tsprojectV2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.ysraltn.tsprojectV2.dto.ConsumableTypeDto;
import tech.ysraltn.tsprojectV2.model.Consumable;
import tech.ysraltn.tsprojectV2.model.ConsumableType;
import tech.ysraltn.tsprojectV2.repository.ConsumableRepository;
import tech.ysraltn.tsprojectV2.repository.ConsumableTypeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsumableTypeService {

    private final ConsumableTypeRepository consumableTypeRepository;
    private final ConsumableRepository consumableRepository;

    public List<ConsumableTypeDto> getAllTypes() {
        return consumableTypeRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<ConsumableTypeDto> getTypeById(Long id) {
        return consumableTypeRepository.findById(id).map(this::convertToDto);
    }

    public Optional<ConsumableTypeDto> createType(ConsumableTypeDto dto) {
        return consumableRepository.findById(dto.getConsumableId()).map(consumable -> {
            ConsumableType type = ConsumableType.builder()
                    .typeName(dto.getTypeName())
                    .consumable(consumable)
                    .build();
            return convertToDto(consumableTypeRepository.save(type));
        });
    }

    public boolean deleteType(Long id) {
        if (consumableTypeRepository.existsById(id)) {
            consumableTypeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private ConsumableTypeDto convertToDto(ConsumableType type) {
        return ConsumableTypeDto.builder()
                .id(type.getId())
                .typeName(type.getTypeName())
                .consumableId(type.getConsumable().getId())
                .consumableName(type.getConsumable().getName())
                .build();
    }

    public List<ConsumableTypeDto> getTypesByConsumableId(Long consumableId) {
        return consumableTypeRepository.findByConsumableId(consumableId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
