package gg.dstore.admin.service.like;

import gg.dstore.admin.domain.repository.LikeRepo;
import gg.dstore.admin.domain.repository.ProjectListRepo;
import gg.dstore.domain.entity.ProjectEntity;
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
