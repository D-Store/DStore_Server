package gg.jominsubyungsin.admin.service.project;

import gg.jominsubyungsin.admin.domain.dto.project.dataIgnore.ASelectPJDto;
import gg.jominsubyungsin.admin.domain.dto.project.response.ProjectListResponse;
import gg.jominsubyungsin.admin.domain.dto.user.dataIgnore.ASelectUserDto;
import gg.jominsubyungsin.admin.domain.repository.ProjectListRepo;
import gg.jominsubyungsin.domain.dto.project.dataIgnore.SelectProjectDto;
import gg.jominsubyungsin.domain.entity.ProjectEntity;
import gg.jominsubyungsin.domain.entity.ProjectTagConnectEntity;
import gg.jominsubyungsin.domain.entity.ProjectUserConnectEntity;
import gg.jominsubyungsin.domain.entity.UserEntity;
import gg.jominsubyungsin.domain.repository.ProjectRepository;
import gg.jominsubyungsin.domain.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminPJServiceImpl implements AdminPJService {
    private final ProjectRepository projectRepository;
    private final ProjectListRepo projectListRepo;

    @Override
    public List<ProjectEntity> getProjectAll(){
        List<ProjectEntity> pjList;

        try {
            pjList = projectRepository.findAll();
            return pjList;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Response getProjects(Pageable pageable) {
        ProjectListResponse response = new ProjectListResponse();
        List<ASelectPJDto> pjDtos = new ArrayList<>();

        try {
            Page<ProjectEntity> projectEntityPage = projectListRepo.findAll(pageable);
            List<ProjectEntity> projectEntities = projectEntityPage.getContent();

            for (ProjectEntity projectEntity : projectEntities) {
                List<ASelectUserDto> userDtos = new ArrayList<>();

                for (ProjectUserConnectEntity connectEntity : projectEntity.getUsers()) {
                    ASelectUserDto userDto = new ASelectUserDto(connectEntity.getUser());
                    userDtos.add(userDto);
                }

                List<String> tags = new ArrayList<>();
                for (ProjectTagConnectEntity connectEntity : projectEntity.getTags()) {
                    tags.add(connectEntity.getTag().getTag());
                }

                ASelectPJDto aSelectPJDto = new ASelectPJDto(projectEntity, userDtos, tags);
                pjDtos.add(aSelectPJDto);
            }

            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("프로젝트 보기 성공");
            response.setProjectList(pjDtos);
            response.setTotalPages(projectEntityPage.getTotalPages());

            return response;
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러");
        }

    }
//
//    @Override
//    public List<ProjectEntity> getProjectById(Long id){
//        List<ProjectEntity> pjList;
//
//        try {
//            pjList = projectRepository.findByUsers(id);
//            return pjList;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }

    @Override
    public List<ProjectEntity> dropProject(Long id) {
        List<ProjectEntity> pjList;

        try {
            projectRepository.deleteById(id);
            pjList = projectRepository.findAll();
            return pjList;
        } catch (Exception e) {
            throw e;
        }
    }
}
