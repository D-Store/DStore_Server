package gg.dstore.admin.service.user;

import gg.dstore.admin.domain.dto.user.dataIgnore.ASelectUserDto;
import gg.dstore.admin.enums.SearchMode;
import gg.dstore.domain.entity.UserEntity;
import gg.dstore.domain.response.Response;
import gg.dstore.enums.Role;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminUserService {
    void dropUser(Long id);
    void addPerUser(UserEntity target);

    void delPerUser(UserEntity target);
    List<ASelectUserDto> getAdminUserList();
    List<ASelectUserDto> getGeneralUserList();
    Response getUserList(Pageable pageable, Role role);

    Response searchUser(Pageable pageable, Role role, SearchMode mode, String keyword);

    UserEntity isThereUser(Long id);
}
