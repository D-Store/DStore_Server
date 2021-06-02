package gg.jominsubyungsin.admin.service.project;

import gg.jominsubyungsin.domain.entity.ProjectEntity;
import gg.jominsubyungsin.domain.response.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminPJService {
    List<ProjectEntity> getProjectAll();

//    List<ProjectEntity> getProjectById(Long id);

    Response getProjects(Pageable pageable);

    List<ProjectEntity> dropProject(Long id);
}
