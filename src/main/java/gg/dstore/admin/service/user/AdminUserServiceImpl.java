package gg.dstore.admin.service.user;
import gg.dstore.admin.domain.dto.user.dataIgnore.ASelectUserDto;
import gg.dstore.admin.domain.dto.user.response.UserListResponse;
import gg.dstore.admin.domain.repository.UserDetailRepository;
import gg.dstore.admin.enums.SearchMode;
import gg.dstore.domain.entity.UserEntity;
import gg.dstore.admin.domain.repository.UserListRepository;
import gg.dstore.domain.response.Response;
import gg.dstore.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminUserServiceImpl implements AdminUserService {
    private final UserListRepository userListRepository;
    private final UserDetailRepository userRepository;

    @Override
    public List<ASelectUserDto> getAdminUserList(){
        List<ASelectUserDto> adminUserList = new ArrayList<>();

        List<UserEntity> userEntities;

        try {
            userEntities = userRepository.findByRole(Role.ADMIN);

            for (UserEntity userEntity : userEntities) {
                System.out.println("admin user's id : " + userEntity.getId());
                ASelectUserDto userDto = new ASelectUserDto(userEntity);
                adminUserList.add(userDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return adminUserList;
    }

    @Override
    public List<ASelectUserDto> getGeneralUserList(){
        List<ASelectUserDto> generalUserList = new ArrayList<>();

        List<UserEntity> userEntities;

        try {
            userEntities = userRepository.findByRole(Role.USER);

            for (UserEntity userEntity:userEntities) {
                System.out.println("general user's id : " + userEntity.getId());
                ASelectUserDto userDto = new ASelectUserDto(userEntity);
                generalUserList.add(userDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return generalUserList;
    }

    @Override
    public Response getUserList(Pageable pageable, Role role) {
        UserListResponse response = new UserListResponse();
        Page<UserEntity> users;
        List<ASelectUserDto> generalUserList = new ArrayList<>();

        try {
            users = userListRepository.findByRole(pageable, role);
            for (UserEntity u : users) {
                ASelectUserDto userDto = new ASelectUserDto(u);
                generalUserList.add(userDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        response.setHttpStatus(HttpStatus.OK);
        response.setMessage("유저 보기 성공");
        response.setUsers(generalUserList);
        response.setTotalPages(users.getTotalPages());

        return response;
    }

    /**
     * 유저 삭제
     * @param id
     */
    @Override
    @Transactional
    public void dropUser(Long id){
        try {
            UserEntity user1 = isThereUser(id);
            user1.setOnDelete(true);
            userRepository.save(user1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러");
        }
    }

    /**
     * 유저 admin 권한 추가
     *
     */
    @Override
    @Transactional
    public void addPerUser(UserEntity target){
        target.setRole(Role.ADMIN);
        try {
            userRepository.save(target);
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 유저 admin 권한 삭제
     */
    @Override
    @Transactional
    public void delPerUser(UserEntity target){
        target.setRole(Role.USER);
        try {
            userRepository.save(target);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Response searchUser(Pageable pageable, Role role, SearchMode mode, String keyword) {
        UserListResponse response = new UserListResponse();
        Page<UserEntity> users = null;
        List<ASelectUserDto> userList = new ArrayList<>();

        try {
            if (mode == SearchMode.name) {
                users = userListRepository.findByRoleAndNameContaining(pageable, role, keyword);
            } else if (mode == SearchMode.email) {
                users = userListRepository.findByRoleAndEmailContaining(pageable, role, keyword);
            }

            for (UserEntity u : users) {
                ASelectUserDto userDto = new ASelectUserDto(u);
                userList.add(userDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        response.setHttpStatus(HttpStatus.OK);
        response.setMessage("성공");
        response.setUsers(userList);
        response.setTotalPages(users.getTotalPages());

        return response;
    }

    /**
     * 유저 존재 확인
     * @param id
     * @return target(entity)
     */
    @Override
    public UserEntity isThereUser(Long id){
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "없는 회원입니다")
                );
    }

}
