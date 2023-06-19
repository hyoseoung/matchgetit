package com.matchgetit.backend.config;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    //이 메소드로 @CreatedBy(작성자) 및 @LastModifiedBy(수정자)를 설정함.
    @Override @NotNull
    public Optional<String> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = "";
        if (auth != null) {
            userId = auth.getName();
        }
        return Optional.of(userId);
//        return Optional.of("테스터");
    }

}
