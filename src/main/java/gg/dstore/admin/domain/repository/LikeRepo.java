package gg.dstore.admin.domain.repository;

import gg.dstore.domain.entity.LikeEntity;
import gg.dstore.domain.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepo extends JpaRepository<LikeEntity, Long> {
    Long countByProjectAndState(ProjectEntity projectEntity, Boolean state);
}
