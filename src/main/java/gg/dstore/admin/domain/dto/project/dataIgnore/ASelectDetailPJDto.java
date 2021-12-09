package gg.dstore.admin.domain.dto.project.dataIgnore;

import gg.dstore.admin.domain.dto.comment.dataIgnore.ASelectCommentDto;
import gg.dstore.admin.domain.dto.user.dataIgnore.ASelectUserDto;
import gg.dstore.domain.entity.FileEntity;
import gg.dstore.domain.entity.ProjectEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ASelectDetailPJDto {
    private Long id;
    private String title;
    private String content;
    private Date createAt;
    private Boolean onDelete;
    private List<ASelectUserDto> users;
    private List<String> tags;
    private List<FileEntity> files;
    private Long likeNum;
    private List<ASelectCommentDto> comments;

    public ASelectDetailPJDto(ProjectEntity projectEntity, List<ASelectUserDto> users,
                              List<String> tags, List<FileEntity> files,
                              Long likeNum, List<ASelectCommentDto> comments) {
        this.id = projectEntity.getId();
        this.title = projectEntity.getTitle();
        this.content = projectEntity.getContent();
        this.createAt = projectEntity.getCreateAt();
        this.onDelete = projectEntity.getOnDelete();
        this.users = users;
        this.tags = tags;
        this.files = files;
        this.likeNum = likeNum;
        this.comments = comments;
    }

}
