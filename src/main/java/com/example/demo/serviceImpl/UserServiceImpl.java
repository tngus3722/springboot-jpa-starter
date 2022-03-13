package com.example.demo.serviceImpl;

import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void SignIn(User user) throws Exception {
        userRepository.save(new UserEntity(user));
    }

    @Transactional(readOnly = true)
    @Override
    public User getMe() {
        return UserMapper
                .INSTANCE
                .toUser(userRepository.findById(Long.valueOf(1)).get());
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUser(Integer page, Integer limit) {
        return userRepository.findAll(PageRequest.of(page, limit, Sort.by("id").descending()))
                .stream()
                .map(UserMapper.INSTANCE::toUser)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userRepository.findById(user.getId()).get().updateUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(User user) {
        userRepository.delete(entityManager.getReference(UserEntity.class, user.getId()));
    }
}
