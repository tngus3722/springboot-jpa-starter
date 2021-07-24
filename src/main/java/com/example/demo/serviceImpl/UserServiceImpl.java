package com.example.demo.serviceImpl;

import com.example.demo.entity.MajorEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.wrapper.UserWrapper;
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
        userEntity.setMajorEntityList(majorEntityList);

        userRepository.save(userEntity); // save also major bacause of CASCADE.ALL (PERSIST)
    }

    // TODO 강의의 포인트 Mapstruct
    // 쿼리 결과를 중요하게 봅시다
    @Override
    public User getMe() { // 토큰만들기 귀찮아서 그냥 3번유저로 합니다 ㅎㅎ
        return UserMapper.INSTANCE.toUser(userRepository.findById(Long.valueOf(3)).get());
    }


    // TODO 강의의 포인트 Mapstruct
    // TODO 강의의 포인트 batch_size
    //쿼리결과를 중요하게 봅시다 2번째 메소드와 쿼리갯수가 다르~까요 같은까~요? n+1이 터지지 않습니다.
    @Override
    public List<User> getAllUser() {
        return userRepository.findAll().stream().map(UserMapper.INSTANCE::toUser).collect(Collectors.toList());
    }

    @Override
    public void updateUser(User user){ // 토큰이 없으므로 id도 받겠습니다.
        //UserEntity userEntity = userRepository.findById(user.getId()).get();
        UserEntity userEntity = entityManager.getReference(UserEntity.class, user.getId()); // 이건 터질까요 안터질까요?

        userEntity.setPassword(user.getPassword()); // 이 내용도 entity 안에 update라는 함수를 만들면 참 편해집니다 ,
        // 두번째 친구의 경우 이때  쿼리가 터집니다 즉 실사용시 터지느냐에 대한 차이를 가집니다.
        // fk로 검색할때는 이런 reference 객체를 사용하면 좋습니다.
        userEntity.setNickname(user.getNickname());
        userEntity.setPortalAccount(user.getPortalAccount());


        /***
         *  A collection with cascade="all-delete-orphan" was no longer referenced by the owning entity instance: com.example.demo.entity.UserEntity.majorEntityList
         *  orphan 속성이 있는 경우 이렇게 수정하면 안됩니다. 새로운 컬렉션 녀석은 하이버네이트가 관리하던 친구가 아니라서 에러를 뿜습니다.
         *  orphan 속성이 없는 경우 에러는 나지 않지만 왜냐면 majorEntityList가 영속상태가 아니기 때문인것 같은데 결과적으로 아래 내용은 디비에 반영조차 되지 않습니다.
         */
        List<MajorEntity> majorEntityList = new ArrayList<>();
        for ( int i=0; i<user.getMajorEntityList().size(); i++){
            MajorEntity majorEntity = new MajorEntity();
            majorEntity.setUserEntity(userEntity);
            majorEntity.setMajor(user.getMajorEntityList().get(i).getMajor());
        }
        userEntity.setMajorEntityList(majorEntityList);

    }

    // 고아객체 처리 설정안하면 삭제안시킵니다.
    // cascade랑은 상관 없습니다
    @Override
    public void updateUser2(User user){ // 토큰이 없으므로 id도 받겠습니다.
        //UserEntity userEntity = entityManager.getReference(UserEntity.class, user.getId());
        UserEntity userEntity = userRepository.findById(user.getId()).get();
        userEntity.getMajorEntityList().clear(); //   delete쿼리가 터집니다. 고아객체 제거 속성추가시
    }


    // save를 칠 필요가없음. dirty check
    // cascade 속성만 주었을 시 delete는 하지않고 insert만 계속 시킴.
    @Override
    public void updateUser3(User user){ // 토큰이 없으므로 id도 받겠습니다.
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
    public void deleteUser(User user){ // 귀찮으니 그냥 id만 쓸거지만 이렇게 받아 쓰겠습니다
        // CASCADE ALL 에는 DELETE속성도 들어있기 때문에 영속성전이 효과로 인해 부모객체 삭제시 자식객체도 다 삭제됩니다.
        //select가 터지고 삭제하네요 신기하게도. 왜일까요? 영속컨텍스트에 해당 id가 없기 때문입니다!
        //userRepository.deleteById(user.getId()); // 해당 id가 존재하지 않는 경우 에러가 터지지만 그것은 사실 토큰에서 잡히겠죵 ? ㅎㅎ article같은거면 있는지 체크하는게 좋습니다

        // 아래 방식도 사용가능합니다. 이경우도 똑같이 select가 터지고 삭제가 수행됩니다.
        //userRepository.delete(entityManager.getReference(UserEntity.class,user.getId()));

        //이방식은 어떨까요?
        //@Modifying @Transactional 어노테이션 두개를 붙혀줘야 에러가 나지않습니다.
        // 이 방식은 select가 터지지 않습니다. 하지만 저는 이 방식보다는 위에 방식을 조금 더 권장합니다.
        // 그 이유는 영속성전이가 제대로 실행되지 않기 때문입니다. 영속컨텍스트를 쌩싸고 바로 쿼리를 때려박기 때문에...
        // jpql이 아닌 native query를 쳐도 마찬가지겠죵
        //userRepository.deleteUserByUserId(user.getId());


        // 그러면 엔티티를 가져와서 그거로 쿼리를 치면 어떻게될까요?
        // 역시 바로 쿼리를 쳐버리기때문에 동작하지 않습니다.
        UserEntity userEntity = userRepository.findById(user.getId()).get();
        // userEntity.getMajorEntityList().clear(); orphan remove는 당연히 영속되어있는 상태이기 때문에 동작합니다.
        userRepository.deleteUserByUserEntity(userEntity);

        // 엔티티가 아니라 프록시여도 동작할까요? 동작은 합니다 하지만 역시  CASCADE remove는 작동하지 않아요
        //userRepository.deleteUserByUserEntity(entityManager.getReference(UserEntity.class,user.getId()));
    }


    //서브쿼리 극복
    @Override
    public UserWrapper getSubQueryAndGetDTO(){
        return userRepository.test(Long.valueOf(16));
    }
}
