package tech.ysraltn.tsprojectV2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tech.ysraltn.tsprojectV2.dto.CycleDto;
import tech.ysraltn.tsprojectV2.dto.UserProductAssignmentDto;
import tech.ysraltn.tsprojectV2.model.Product;
import tech.ysraltn.tsprojectV2.model.User;
import tech.ysraltn.tsprojectV2.model.UserProductAssignment;
import tech.ysraltn.tsprojectV2.repository.ProductRepository;
import tech.ysraltn.tsprojectV2.repository.UserProductAssignmentRepository;
import tech.ysraltn.tsprojectV2.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProductAssignmentService {

    private final UserProductAssignmentRepository assignmentRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public void assignProductToUser(Long userId, Long productId) {
        if (assignmentRepository.findByUserIdAndProductId(userId, productId).isPresent()) {
            throw new RuntimeException("Bu kullanıcıya zaten bu ürün atanmış.");
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı."));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Ürün bulunamadı."));

        UserProductAssignment assignment = UserProductAssignment.builder()
                .user(user)
                .product(product)
                .build();

        assignmentRepository.save(assignment);
    }

    public void unassignProductFromUser(Long userId, Long productId) {
        UserProductAssignment assignment = assignmentRepository.findByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new RuntimeException("Atama bulunamadı."));
        assignmentRepository.delete(assignment);
    }

    public List<UserProductAssignmentDto> getAssignmentsByUserId(Long userId) {
        return assignmentRepository.findAllByUserId(userId)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public List<UserProductAssignmentDto> getAssignmentsByProductId(Long productId) {
        return assignmentRepository.findAllByProductId(productId)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    //kullanıcının döngüleri 
    public List<CycleDto> getCycleDtosForUser(Long userId) {
        return assignmentRepository.findCycleDtosByUserId(userId);
    }

    private UserProductAssignmentDto convertToDto(UserProductAssignment assignment) {
        return UserProductAssignmentDto.builder()
                .id(assignment.getId())
                .userId(assignment.getUser().getId())
                .username(assignment.getUser().getUsername())
                .productId(assignment.getProduct().getId())
                .productSerial(assignment.getProduct().getSerial())
                .build();
    }
}
