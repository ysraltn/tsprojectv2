package tech.ysraltn.tsprojectV2.service;

import tech.ysraltn.tsprojectV2.dto.CreateCycleRequest;
import tech.ysraltn.tsprojectV2.dto.CycleDto;
import tech.ysraltn.tsprojectV2.model.Cycle;
import tech.ysraltn.tsprojectV2.model.Product;
import tech.ysraltn.tsprojectV2.model.User;
import tech.ysraltn.tsprojectV2.repository.CycleRepository;
import tech.ysraltn.tsprojectV2.repository.ProductRepository;
import tech.ysraltn.tsprojectV2.repository.UserProductAssignmentRepository;
import tech.ysraltn.tsprojectV2.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CycleService {

    private final CycleRepository cycleRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final UserProductAssignmentRepository assignmentRepository;


    public List<CycleDto> getAllCycles() {
        return cycleRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public List<CycleDto> getCyclesByYear(Integer year) {
        return cycleRepository.findByYear(year)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public CycleDto createCycle(Long userId, CreateCycleRequest cycleRequest) {
        // 1. Ürün var mı?
        Product product = productRepository.findById(cycleRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));

        // 2. Kullanıcı var mı?
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        // 3. Atama kontrolü
        boolean isAssigned = assignmentRepository.findByUserIdAndProductId(user.getId(), product.getId()).isPresent();

        if (!isAssigned) {
            throw new RuntimeException("Bu kullanıcıya bu ürün atanmadığı için döngü verisi girilemez.");
        }

        // 4. Kaydet
        Cycle cycle = Cycle.builder()
                .product(product)
                .year(cycleRequest.getYear())
                .month(cycleRequest.getMonth())
                .cycleCount(cycleRequest.getCycleCount())
                .build();

        cycleRepository.save(cycle);

        return convertToDto(cycle);
    }


    public CycleDto updateCycle(Long cycleId, Long userId, CreateCycleRequest cycleRequest) {
        // 1. Döngü var mı?
        Cycle existingCycle = cycleRepository.findById(cycleId)
                .orElseThrow(() -> new RuntimeException("Döngü bulunamadı"));

        // 2. Ürün var mı?
        Product product = productRepository.findById(cycleRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));

        // 3. Kullanıcı var mı?
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        // 4. Atama kontrolü
        boolean isAssigned = assignmentRepository.findByUserIdAndProductId(user.getId(), product.getId()).isPresent();

        if (!isAssigned) {
            throw new RuntimeException("Bu kullanıcıya bu ürün atanmadığı için döngü verisi düzenlenemez.");
        }

        // 5. Güncelle
        existingCycle.setProduct(product);
        existingCycle.setYear(cycleRequest.getYear());
        existingCycle.setMonth(cycleRequest.getMonth());
        existingCycle.setCycleCount(cycleRequest.getCycleCount());

        cycleRepository.save(existingCycle);

        return convertToDto(existingCycle);
    }

    public void deleteCycle(Long id) {
        cycleRepository.deleteById(id);
    }

    public List<CycleDto> getCyclesByYearAndUser(Integer year, Long userId) {
        List<Cycle> cycles = cycleRepository.findByYearAndProduct_Responsible_Id(year, userId);
    
        return cycles.stream()
        .map(this::convertToDto)
        .toList();
    }

    public List<CycleDto> getCyclesWithFilters(Integer year, Integer month, Long userId, Long institutionId) {
        List<Cycle> cycles = cycleRepository.findCyclesWithFilters(year, month, userId, institutionId);
        
        return cycles.stream()
                .map(this::convertToDto)
                .toList();
    }
    
    

    public CycleDto convertToDto(Cycle cycle) {
        Product p = cycle.getProduct();

        return CycleDto.builder()
                .id(cycle.getId())
                .year(cycle.getYear())
                .month(cycle.getMonth())
                .cycleCount(cycle.getCycleCount())

                .productId(p.getId())
                .productSerial(p.getSerial())
                .productType(p.getType())
                .productBrand(p.getBrand())
                .productModel(p.getModel())
                .productStatus(p.getStatus())

                .institutionId(p.getInstitution() != null ? p.getInstitution().getId() : null)
                .institutionName(p.getInstitution() != null ? p.getInstitution().getName() : null)
                .institutionCity(p.getInstitution() != null ? p.getInstitution().getCity() : null)

                .ownerId(p.getOwner() != null ? p.getOwner().getId() : null)
                .ownerName(p.getOwner() != null ? p.getOwner().getName() : null)

                .responsibleId(p.getResponsible() != null ? p.getResponsible().getId() : null)
                .responsibleFullName(p.getResponsible() != null ? p.getResponsible().getName() : null)

                .build();
    }
}
