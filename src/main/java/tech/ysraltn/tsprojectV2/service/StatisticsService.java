package tech.ysraltn.tsprojectV2.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tech.ysraltn.tsprojectV2.dto.StatisticsDto;
import tech.ysraltn.tsprojectV2.repository.InstitutionRepository;
import tech.ysraltn.tsprojectV2.repository.ProductRepository;
import tech.ysraltn.tsprojectV2.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final ProductRepository productRepository;
    private final InstitutionRepository institutionRepository;
    private final UserRepository userRepository;


    public StatisticsDto getStatistics() {
        return new StatisticsDto(
                productRepository.count(),
                productRepository.countByModel("100S"),
                productRepository.countByModelContaining("100NX"),
                productRepository.countByModel("NX"),
                institutionRepository.count(),
                userRepository.count()
        );
    }
}

