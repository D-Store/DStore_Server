package gg.dstore.domain.repository;

import gg.dstore.domain.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
	Optional<ProjectEntity> findByIdAndOnDelete(Long id, Boolean onDelete);
	@Query(value = "SELECT count(DISTINCT project) FROM project_user_connect WHERE user = (:user) AND project IN (SELECT id FROM project WHERE on_delete = (:onDelete))", nativeQuery = true)
	Long countByUsersAndOnDelete(@Param("user") Long user, @Param("onDelete") int onDelete);
	void deleteById(Long id);
	List<ProjectEntity> findByUsers(Long id);
	@Query(value = "select count(DISTINCT project_id) FROM project_tag_connect where tag_id in (:tags) and project_id in (select id from project where on_delete = 0)", nativeQuery = true)
	Long countProjectTags(@Param("tags") List<Long> tag);
	Long countByOnDelete(Boolean onDelete);
	List<ProjectEntity> findAllByIdIsInOrderByIdDesc(List<Long> id);

}
