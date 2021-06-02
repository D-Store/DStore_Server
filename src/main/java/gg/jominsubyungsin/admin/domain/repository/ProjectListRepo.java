package gg.jominsubyungsin.admin.domain.repository;

import gg.jominsubyungsin.domain.entity.ProjectEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProjectListRepo extends PagingAndSortingRepository<ProjectEntity, Long> {

}
