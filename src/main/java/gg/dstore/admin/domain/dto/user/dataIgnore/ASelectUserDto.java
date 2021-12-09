package gg.dstore.admin.domain.dto.user.dataIgnore;

import gg.dstore.domain.entity.UserEntity;
import gg.dstore.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ASelectUserDto {
    private Long id;
    private String name;
    private String email;
    private String profileImage;
    private Role role;
    private Boolean onDelete;

    public ASelectUserDto(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.name = userEntity.getName();
        this.email = userEntity.getEmail();
        this.profileImage = userEntity.getProfileImage();
        this.role = userEntity.getRole();
        this.onDelete = userEntity.getOnDelete();
    }
}
