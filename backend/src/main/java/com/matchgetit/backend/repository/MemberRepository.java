package com.matchgetit.backend.repository;

import com.matchgetit.backend.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    MemberEntity findByEmail(String email); //로그인 때 사용
    MemberEntity findByUserId(int userId);//대기열에서 회원정보 찾을 시 사용

    MemberEntity findByPn(String pn);


}

