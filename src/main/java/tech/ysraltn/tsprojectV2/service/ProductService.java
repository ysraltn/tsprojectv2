package tech.ysraltn.tsprojectV2.service;

import tech.ysraltn.tsprojectV2.dto.ProductDto;
import tech.ysraltn.tsprojectV2.dto.ProductRequestDto;
import tech.ysraltn.tsprojectV2.model.Product;
import tech.ysraltn.tsprojectV2.repository.InstitutionRepository;
import tech.ysraltn.tsprojectV2.repository.ProductRepository;
import tech.ysraltn.tsprojectV2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final InstitutionRepository institutionRepository;
    private final UserRepository userRepository;

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToDto)
                .toList();
    }
    

    public Optional<ProductDto> getProductById(Long id) {
        return productRepository.findById(id).map(this::convertToDto);
    }
    

    public Optional<ProductDto> getProductBySerial(String serial) {
        return productRepository.findBySerial(serial).map(this::convertToDto);
    }

    public List<ProductDto> getProductsByInstitution(Long institutionId) {
        List<Product> products = productRepository.findByInstitution_Id(institutionId);
        return products.stream()
                .map(this::convertToDto)
                .toList();
    }
    

    public ProductDto createProduct(ProductRequestDto request) {
    Product product = Product.builder()
            .serial(request.getSerial())
            .type(request.getType())
            .brand(request.getBrand())
            .model(request.getModel())
            .status(request.getStatus())
            .institution(institutionRepository.findById(request.getInstitutionId())
                    .orElseThrow(() -> new RuntimeException("Institution not found")))
            .owner(institutionRepository.findById(request.getOwnerId())
                    .orElseThrow(() -> new RuntimeException("Owner not found")))
            .responsible(
                    request.getResponsibleId() != null ?
                            userRepository.findById(request.getResponsibleId())
                                    .orElseThrow(() -> new RuntimeException("Responsible user not found"))
                            : null
            )
            .build();

    Product saved = productRepository.save(product);
    return convertToDto(saved);
}

public ProductDto updateProduct(Long id, ProductRequestDto request) {
    Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));

    product.setSerial(request.getSerial());
    product.setType(request.getType());
    product.setBrand(request.getBrand());
    product.setModel(request.getModel());
    product.setStatus(request.getStatus());
    product.setInstitution(institutionRepository.findById(request.getInstitutionId())
            .orElseThrow(() -> new RuntimeException("Institution not found")));
    product.setOwner(institutionRepository.findById(request.getOwnerId())
            .orElseThrow(() -> new RuntimeException("Owner not found")));
    product.setResponsible(
            request.getResponsibleId() != null ?
                    userRepository.findById(request.getResponsibleId())
                            .orElseThrow(() -> new RuntimeException("Responsible user not found"))
                    : null
    );

    Product updated = productRepository.save(product);
    return convertToDto(updated);
}



    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public ProductDto convertToDto(Product product) {
    return ProductDto.builder()
            .id(product.getId())
            .serial(product.getSerial())
            .type(product.getType())
            .brand(product.getBrand())
            .model(product.getModel())
            .status(product.getStatus())

            .institutionId(product.getInstitution() != null ? product.getInstitution().getId() : null)
            .institutionName(product.getInstitution() != null ? product.getInstitution().getName() : null)

            .ownerId(product.getOwner() != null ? product.getOwner().getId() : null)
            .ownerName(product.getOwner() != null ? product.getOwner().getName() : null)

            .responsibleId(product.getResponsible() != null ? product.getResponsible().getId() : null)
            .responsibleFullName(product.getResponsible() != null
                    ? product.getResponsible().getName()
                    : null)

            .build();
}

}
