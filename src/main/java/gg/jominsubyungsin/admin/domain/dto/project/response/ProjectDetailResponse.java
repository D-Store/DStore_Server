package gg.jominsubyungsin.admin.domain.dto.project.response;

import gg.jominsubyungsin.admin.domain.dto.project.dataIgnore.ASelectDetailPJDto;
import gg.jominsubyungsin.domain.response.Response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDetailResponse extends Response {
    private ASelectDetailPJDto project;
}
