package gg.jominsubyungsin.admin.domain.dto.project.response;

import gg.jominsubyungsin.admin.domain.dto.project.dataIgnore.ASelectPJDto;
import gg.jominsubyungsin.domain.entity.ProjectEntity;
import gg.jominsubyungsin.domain.response.Response;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectListResponse extends Response {
    private List<ASelectPJDto> projectList;
    private int totalPages;
}
