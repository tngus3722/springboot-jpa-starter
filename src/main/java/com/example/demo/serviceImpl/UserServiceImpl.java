package com.example.demo.serviceImpl;

import com.example.demo.dto.request.UserSignUpRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public UserResponse singUp(UserSignUpRequest userSignUpRequest) {
        return UserMapper.INSTANCE.toUser(userRepository.save(UserEntity.from(userSignUpRequest)));
    }

    @Override
    public UserResponse getMe(Long userId) {
        return UserMapper.INSTANCE.toUser(this.getUserEntity(userId));
    }

    @Override
    public List<UserResponse> getUsers(Integer page, Integer limit) {
        return userRepository.findAll(PageRequest.of(page, limit)).stream()
                .map(UserMapper.INSTANCE::toUser)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserResponse updateUser(Long userId, UserUpdateRequest userUpdateRequest) {
        this.getUserEntity(userId).update(userUpdateRequest);
        entityManager.flush();
        entityManager.clear(); // for updatedAt

        return this.getMe(userId);
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    private UserEntity getUserEntity(Long userId) {
        return userRepository.findById(userId).orElseThrow(RuntimeException::new);
    }
}
