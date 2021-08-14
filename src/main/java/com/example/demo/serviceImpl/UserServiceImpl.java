package com.example.demo.serviceImpl;

import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void SignIn(User user) throws Exception {
        userRepository.save(new UserEntity(user));
    }

    @Override
    public User getMe() {
        return UserMapper.INSTANCE.toUser(userRepository.findById(Long.valueOf(1)).get());
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll().stream().map(UserMapper.INSTANCE::toUser).collect(Collectors.toList());
    }

    @Override
    public void updateUser(User user) {
        userRepository.findById(user.getId()).get().updateUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(entityManager.getReference(UserEntity.class, user.getId()));
    }
}
