package gg.dstore.admin.domain.dto.project.response;

import gg.dstore.admin.domain.dto.project.dataIgnore.ASelectDetailPJDto;
import gg.dstore.domain.response.Response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDetailResponse extends Response {
    private ASelectDetailPJDto project;
}
