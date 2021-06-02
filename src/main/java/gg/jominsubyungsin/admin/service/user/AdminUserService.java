package gg.jominsubyungsin.admin.service.user;

import gg.jominsubyungsin.admin.domain.dto.user.dataIgnore.ASelectUserDto;
import gg.jominsubyungsin.admin.enums.SearchMode;
import gg.jominsubyungsin.domain.entity.UserEntity;
import gg.jominsubyungsin.domain.response.Response;
import gg.jominsubyungsin.enums.Role;
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
