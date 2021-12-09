package gg.dstore.admin.domain.dto.project.response;

import gg.dstore.admin.domain.dto.project.dataIgnore.ASelectPJDto;
import gg.dstore.domain.response.Response;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectListResponse extends Response {
    private List<ASelectPJDto> projectList;
    private int totalPages;
}
