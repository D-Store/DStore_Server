package gg.dstore.admin.service.project;

import gg.dstore.domain.entity.ProjectEntity;
import gg.dstore.domain.response.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminPJService {
    List<ProjectEntity> getProjectAll();

    Response getProjects(Pageable pageable);

    void dropProject(Long id);

    Response getProjectDetail(ProjectEntity projectEntity);

    ProjectEntity isThereProject(Long id);
}
