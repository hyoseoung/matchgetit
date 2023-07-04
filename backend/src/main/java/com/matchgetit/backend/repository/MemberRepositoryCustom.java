package com.matchgetit.backend.repository;

import com.matchgetit.backend.dto.AdminPageSearchUserDTO;
import com.matchgetit.backend.dto.AdminPageUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberRepositoryCustom {
    Page<AdminPageUserDTO> getUserListPageBy(AdminPageSearchUserDTO searchUserDTO, Pageable pageable);
}
