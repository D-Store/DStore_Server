package gg.dstore.admin.domain.dto.project.dataIgnore;

import gg.dstore.admin.domain.dto.user.dataIgnore.ASelectUserDto;
import gg.dstore.domain.entity.FileEntity;
import gg.dstore.domain.entity.ProjectEntity;
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
    private Long likeNum;

    public ASelectPJDto(ProjectEntity projectEntity, List<ASelectUserDto> selectUserDto, List<String> tags, Long likeNum) {
        this.id = projectEntity.getId();
        this.title = projectEntity.getTitle();
        this.createAt = projectEntity.getCreateAt();
        this.onDelete = projectEntity.getOnDelete();
        this.mainPhoto = projectEntity.getFiles().get(0);
        this.tags = tags;
        this.users = selectUserDto;
        this.likeNum = likeNum;
    }
}
