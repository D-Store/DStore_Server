package gg.dstore.admin.domain.repository;

import gg.dstore.domain.entity.BannerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepo extends JpaRepository<BannerEntity, Long> {
}
