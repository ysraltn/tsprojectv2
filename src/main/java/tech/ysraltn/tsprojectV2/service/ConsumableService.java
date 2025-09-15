package tech.ysraltn.tsprojectV2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tech.ysraltn.tsprojectV2.dto.ConsumableDto;
import tech.ysraltn.tsprojectV2.model.Consumable;
import tech.ysraltn.tsprojectV2.model.ConsumableType;
import tech.ysraltn.tsprojectV2.repository.ConsumableRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsumableService {

    private final ConsumableRepository consumableRepository;

    public Optional<ConsumableDto> getConsumableById(Long id) {
        return consumableRepository.findById(id)
                .map(this::convertToDto);
    }
    
    public boolean deleteConsumable(Long id) {
        if (consumableRepository.existsById(id)) {
            consumableRepository.deleteById(id);
            return true;
        }
        return false;
    }
    

    public List<ConsumableDto> getAllConsumables() {
        List<Consumable> consumables = consumableRepository.findAll();
        return consumables.stream()
                .map(this::convertToDto)
                .toList();
    }
    
    public ConsumableDto createConsumable(ConsumableDto dto) {
        Consumable consumable = convertToEntity(dto);
        Consumable saved = consumableRepository.save(consumable);
        return convertToDto(saved);
    }

    public Optional<ConsumableDto> updateConsumable(Long id, ConsumableDto dto) {
    return consumableRepository.findById(id).map(consumable -> {
        consumable.setName(dto.getName());
        consumable.setDescription(dto.getDescription());

        // Türler güncelleniyor
        List<ConsumableType> types = dto.getTypes().stream()
                .map(typeDto -> ConsumableType.builder()
                        .id(typeDto.getId())
                        .typeName(typeDto.getTypeName())
                        .consumable(consumable)
                        .build())
                .toList();

        consumable.setTypes(types);

        Consumable updated = consumableRepository.save(consumable);
        return convertToDto(updated);
    });
}

    
    private ConsumableDto convertToDto(Consumable c) {
        return ConsumableDto.builder()
                .id(c.getId())
                .name(c.getName())
                .description(c.getDescription())
                .build();
    }
    
    private Consumable convertToEntity(ConsumableDto dto) {
        return Consumable.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }
    
}
