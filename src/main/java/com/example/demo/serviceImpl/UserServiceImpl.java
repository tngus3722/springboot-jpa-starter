package com.example.demo.serviceImpl;

import com.example.demo.entity.AddressEntity;
import com.example.demo.entity.MajorEntity;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager; // entity manager 는 Thread간 공유하면 될까요 안될까요 ?

    // TODO 강의의 포인트 CASCADE_Persist를 통한 부모객체 저장시 연관된 자식객체들도 db에 저장됨.
    @Override
    public void SignIn(User user) {
        UserEntity userEntity = new UserEntity(); // 생성자를 만들면 좃습니다
        userEntity.setNickname(user.getNickname());
        userEntity.setPassword(user.getPassword());
        userEntity.setPortalAccount(user.getPortalAccount());

        List<MajorEntity> majorEntityList = new ArrayList<MajorEntity>();
        if ( user.getMajorEntityList() != null && !user.getMajorEntityList().isEmpty() ) {
            for (int i = 0; i < user.getMajorEntityList().size(); i++) {
                MajorEntity majorEntity = new MajorEntity();
                majorEntity.setMajor(user.getMajorEntityList().get(i).getMajor());
                majorEntity.setUserEntity(userEntity);
                majorEntityList.add(majorEntity);
            }
        }
        List<AddressEntity> addressEntities = new ArrayList<>();
        if ( user.getMajorEntityList() != null && !user.getMajorEntityList().isEmpty() ) {
            for (int i = 0; i < user.getAddressEntities().size(); i++) {
                AddressEntity addressEntity = new AddressEntity();
                addressEntity.setAddress(user.getAddressEntities().get(i).getAddress());
                addressEntity.setUserEntity(userEntity);
                addressEntities.add(addressEntity);
            }
        }
        userEntity.setAddressEntities(addressEntities);
        userEntity.setMajorEntityList(majorEntityList);

        userRepository.save(userEntity); // save also major bacause of CASCADE.ALL (PERSIST)
    }

    @Override
    public User getMe() { // 토큰만들기 귀찮아서 그냥 3번유저로 합니다 ㅎㅎ
        return UserMapper.INSTANCE.toUser(userRepository.findById(Long.valueOf(25)).get());
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll().stream().map(UserMapper.INSTANCE::toUser).collect(Collectors.toList());
    }

    // save를 칠 필요가없음. dirty check
    // cascade 속성만 주었을 시 delete는 하지않고 insert만 계속 시킴.
    @Override
    public void updateUser(User user){ // 토큰이 없으므로 id도 받겠습니다.
        UserEntity userEntity = userRepository.findById(user.getId()).get();

        userEntity.setPassword(user.getPassword());
        userEntity.setNickname(user.getNickname());
        userEntity.setPortalAccount(user.getPortalAccount());

        // 올바른 수정방법..
        List<MajorEntity> majorEntityList = userEntity.getMajorEntityList();
        majorEntityList.clear(); // opphan 속성 있어야 작동함.
        for ( int i=0; i<user.getMajorEntityList().size(); i++){
            MajorEntity majorEntity = new MajorEntity();
            majorEntity.setUserEntity(userEntity);
            majorEntity.setMajor(user.getMajorEntityList().get(i).getMajor());
            majorEntityList.add(majorEntity); // cascade 속성 있어야 작동함.
        }

    }

    @Override
    public void deleteUser(User user){
        userRepository.delete(entityManager.getReference(UserEntity.class,user.getId()));
    }

}
