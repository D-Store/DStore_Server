package gg.jominsubyungsin.admin.service.project;

import gg.jominsubyungsin.admin.domain.dto.comment.dataIgnore.ASelectCommentDto;
import gg.jominsubyungsin.admin.domain.dto.project.dataIgnore.ASelectDetailPJDto;
import gg.jominsubyungsin.admin.domain.dto.project.dataIgnore.ASelectPJDto;
import gg.jominsubyungsin.admin.domain.dto.project.response.ProjectDetailResponse;
import gg.jominsubyungsin.admin.domain.dto.project.response.ProjectListResponse;
import gg.jominsubyungsin.admin.domain.dto.user.dataIgnore.ASelectUserDto;
import gg.jominsubyungsin.admin.domain.dto.user.dataIgnore.ASimpleUserDto;
import gg.jominsubyungsin.admin.domain.repository.LikeRepo;
import gg.jominsubyungsin.admin.domain.repository.ProjectListRepo;
import gg.jominsubyungsin.admin.service.like.AdminLikeService;
import gg.jominsubyungsin.domain.entity.*;
import gg.jominsubyungsin.domain.repository.ProjectRepository;
import gg.jominsubyungsin.domain.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminPJServiceImpl implements AdminPJService {
    private final AdminLikeService likeService;

    private final ProjectRepository projectRepository;
    private final ProjectListRepo projectListRepo;
    private final LikeRepo likeRepo;

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

                Long likeNum;
                likeNum = likeService.getLikeNum(projectEntity.getId());

                ASelectPJDto aSelectPJDto = new ASelectPJDto(projectEntity, userDtos, tags, likeNum);
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

    @Override
    @Transactional
    public void dropProject(Long id) {

        try {
            projectRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Response getProjectDetail(ProjectEntity projectEntity) {
        ASelectDetailPJDto pjDto;
        ProjectDetailResponse response = new ProjectDetailResponse();

        try {
            Long likeNum = likeService.getLikeNum(projectEntity.getId());
            List<ASelectCommentDto> commentDtos = new ArrayList<>();
            List<ASelectUserDto> userDtos = new ArrayList<>();
            List<String> tags = new ArrayList<>();
            List<FileEntity> fileEntities = new ArrayList<>(projectEntity.getFiles());

            List<CommentEntity> comments = projectEntity.getComments();
            for (CommentEntity comment : comments) {
                ASimpleUserDto simpleUserDto = new ASimpleUserDto(comment.getUser());
                ASelectCommentDto commentDto = new ASelectCommentDto(comment, simpleUserDto);

                commentDtos.add(commentDto);
            }

            List<ProjectUserConnectEntity> users = projectEntity.getUsers();
            for (ProjectUserConnectEntity user : users) {
                ASelectUserDto userDto = new ASelectUserDto(user.getUser());

                userDtos.add(userDto);
            }

            for (ProjectTagConnectEntity tag : projectEntity.getTags()) {
                tags.add(tag.getTag().getTag());
            }

            pjDto = new ASelectDetailPJDto(projectEntity, userDtos, tags, fileEntities, likeNum, commentDtos);

            response.setProject(pjDto);
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("프로젝트 보기 성공");

            return response;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 프로젝트 엔티티 가져오기
     */
    @Override
    public ProjectEntity isThereProject(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(
                        () -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "없는 프로젝트 입니다")
                );
    }
}
