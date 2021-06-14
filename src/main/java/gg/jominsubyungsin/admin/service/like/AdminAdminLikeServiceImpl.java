package gg.jominsubyungsin.admin.service.like;

import gg.jominsubyungsin.admin.domain.repository.LikeRepo;
import gg.jominsubyungsin.admin.domain.repository.ProjectListRepo;
import gg.jominsubyungsin.admin.service.project.AdminPJService;
import gg.jominsubyungsin.domain.entity.LikeEntity;
import gg.jominsubyungsin.domain.entity.ProjectEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@RequiredArgsConstructor
@Service
public class AdminAdminLikeServiceImpl implements AdminLikeService {
    private final ProjectListRepo projectRepository;
    private final LikeRepo likeRepository;

    @Override
    public Long getLikeNum(Long id) {
        ProjectEntity projectEntity = projectRepository.findById(id)
                .orElseThrow(
                        () -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "없는 프로젝트 입니다")
                );

        try {
            return likeRepository.countByProjectAndState(projectEntity, true);
        } catch (Exception e) {
            throw e;
        }
    }

}
