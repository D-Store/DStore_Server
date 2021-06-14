package gg.jominsubyungsin.admin.domain.dto.comment.dataIgnore;

import gg.jominsubyungsin.admin.domain.dto.user.dataIgnore.ASimpleUserDto;
import gg.jominsubyungsin.domain.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ASelectCommentDto {
    private Long id;
    private String comment;
    private Date createAt;
    private ASimpleUserDto user;

    public ASelectCommentDto(CommentEntity commentEntity, ASimpleUserDto userDto) {
        this.id = commentEntity.getId();
        this.comment = commentEntity.getComment();
        this.createAt = commentEntity.getCreateAt();
        this.user = userDto;
    }
}
