package com.project.matchgetit.repository;

import com.project.matchgetit.dto.AdminPageUserDTO;
import com.project.matchgetit.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryCustom {
//    Page<User> getUserListPage2(Pageable pageable);

    Page<AdminPageUserDTO> getUserListPageBy(Pageable pageable);
}
