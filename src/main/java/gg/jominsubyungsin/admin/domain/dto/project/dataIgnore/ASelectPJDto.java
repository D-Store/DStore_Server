package gg.jominsubyungsin.admin.domain.dto.project.dataIgnore;

import gg.jominsubyungsin.admin.domain.dto.user.dataIgnore.ASelectUserDto;
import gg.jominsubyungsin.domain.entity.FileEntity;
import gg.jominsubyungsin.domain.entity.ProjectEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ASelectPJDto {
    private Long id;
    private String title;
    private FileEntity mainPhoto;
    private Date createAt;
    private boolean onDelete;
    private List<ASelectUserDto> users;
    private List<String> tags;

    public ASelectPJDto(ProjectEntity projectEntity, List<ASelectUserDto> selectUserDto, List<String> tags) {
        this.id = projectEntity.getId();
        this.title = projectEntity.getTitle();
        this.createAt = projectEntity.getCreateAt();
        this.onDelete = projectEntity.getOnDelete();
        this.mainPhoto = projectEntity.getFiles().get(0);
        this.tags = tags;
        this.users = selectUserDto;
    }
}
