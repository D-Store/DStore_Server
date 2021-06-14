package gg.jominsubyungsin.admin.domain.repository;

import gg.jominsubyungsin.domain.entity.LikeEntity;
import gg.jominsubyungsin.domain.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepo extends JpaRepository<LikeEntity, Long> {
    Long countByProjectAndState(ProjectEntity projectEntity, Boolean state);
}
