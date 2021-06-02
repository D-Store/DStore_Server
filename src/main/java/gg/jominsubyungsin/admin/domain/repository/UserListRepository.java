package gg.jominsubyungsin.admin.domain.repository;

import gg.jominsubyungsin.domain.entity.UserEntity;
import gg.jominsubyungsin.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserListRepository extends PagingAndSortingRepository<UserEntity, Long> {
    Page<UserEntity> findByRole(Pageable pageable, Role role);
    Page<UserEntity> findByRoleAndNameContaining(Pageable pageable, Role role, String name);
    Page<UserEntity> findByRoleAndEmailContaining(Pageable pageable, Role role, String email);
}
