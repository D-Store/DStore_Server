package gg.dstore.admin.domain.repository;

import gg.dstore.domain.entity.ProjectEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProjectListRepo extends PagingAndSortingRepository<ProjectEntity, Long> {

}
