package com.matchgetit.backend.repository;

import com.matchgetit.backend.constant.LogInType;
import com.matchgetit.backend.constant.ManagerSupportStatus;
import com.matchgetit.backend.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long>, MemberRepositoryCustom {
    MemberEntity findByEmail(String email); //로그인 때 사용
    MemberEntity findByUserId(Long userId);//대기열에서 회원정보 찾을 시 사용.
    MemberEntity findByPn(String pn);
    List<MemberEntity> findByLoginType(LogInType loginType);
    List<MemberEntity> findByManagerSupportStatus(ManagerSupportStatus managerSupportStatus); // 매니저 지원 상태로 회원 검색
    // Dashboard 사용
    long countByRegDate(Date regDate);
    long countByRegDateBefore(Date regDate);
    List<MemberEntity> findByNameContaining(String name); //이름으로 검색
    @Query("SELECT m FROM MemberEntity m where m.email= :email")
    MemberEntity findPwByEmail(@Param("email") String email); //아이디 찾기 시 사용

    @Query("SELECT m FROM MemberEntity m where m.name = :name And m.pn = :pn")
    MemberEntity findEmailByPhone(@Param("name") String name, @Param("pn") String pn);

}

