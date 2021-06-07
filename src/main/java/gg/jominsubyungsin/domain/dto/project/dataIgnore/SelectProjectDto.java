package gg.jominsubyungsin.domain.dto.project.dataIgnore;

import gg.jominsubyungsin.domain.dto.user.dataIgnore.SelectUserDto;
import gg.jominsubyungsin.domain.entity.FileEntity;
import gg.jominsubyungsin.domain.entity.ProjectEntity;
import gg.jominsubyungsin.domain.entity.TagEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class SelectProjectDto {
	private Long id;
	private String title;
	private FileEntity mainPhoto;
	private Date createAt;
	private List<SelectUserDto> users;
	private List<String> tags;
	private Long likeNum;
	private Boolean likeState;
	private Long commentNum;

	public SelectProjectDto(ProjectEntity projectEntity, List<SelectUserDto> selectUserDto,List<String> tags, Long likeNum, Boolean likeState, Long commentNum) {
		this.id = projectEntity.getId();
		this.title = projectEntity.getTitle();
		this.createAt = projectEntity.getCreateAt();
		this.mainPhoto = projectEntity.getFiles().get(0);
		this.createAt = projectEntity.getCreateAt();
		this.likeNum = likeNum;
		this.likeState = likeState;
		this.commentNum = commentNum;
		this.tags = tags;
		this.users = selectUserDto;
	}
}
