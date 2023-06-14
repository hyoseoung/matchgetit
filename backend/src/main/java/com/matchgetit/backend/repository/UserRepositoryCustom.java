package com.matchgetit.backend.repository;

import com.matchgetit.backend.dto.AdminPageSearchUserDTO;
import com.matchgetit.backend.dto.AdminPageUserDTO;
import com.matchgetit.backend.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryCustom {
    Page<AdminPageUserDTO> getUserListPageBy(AdminPageSearchUserDTO searchUserDTO, Pageable pageable);
}
