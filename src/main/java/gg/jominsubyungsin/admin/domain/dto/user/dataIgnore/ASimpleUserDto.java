package gg.jominsubyungsin.admin.domain.dto.user.dataIgnore;

import gg.jominsubyungsin.domain.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ASimpleUserDto {
    private Long id;
    private String name;
    private String profileImage;

    public ASimpleUserDto(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.name = userEntity.getName();
        this.profileImage = userEntity.getProfileImage();
    }
}
