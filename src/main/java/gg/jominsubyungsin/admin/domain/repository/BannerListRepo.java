package gg.jominsubyungsin.admin.domain.repository;

import gg.jominsubyungsin.domain.entity.BannerEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BannerListRepo extends PagingAndSortingRepository<BannerEntity, Long> {
}
