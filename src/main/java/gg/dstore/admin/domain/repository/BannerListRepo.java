package gg.dstore.admin.domain.repository;

import gg.dstore.domain.entity.BannerEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BannerListRepo extends PagingAndSortingRepository<BannerEntity, Long> {
}
