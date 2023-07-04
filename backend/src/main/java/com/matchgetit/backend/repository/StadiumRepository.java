package com.matchgetit.backend.repository;

import com.matchgetit.backend.entity.StadiumEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StadiumRepository extends JpaRepository<StadiumEntity,Integer> {
    @Transactional
    List<StadiumEntity> findAll();

    StadiumEntity findByStdName(String StdName);

    Page<StadiumEntity> findByStdNameContaining(String StdName, Pageable pageable);


    @Query(value="SELECT STD_NAME, STD_LINK, STD_PN, STD_IMG_URL, STD_ST_ADR, x_cor, y_cor FROM STADIUM WHERE STD_NAME= :stdName", nativeQuery = true)
    List<Object[]> selectOne(@Param("stdName") String stdName);

    @Transactional
    void deleteByStdName(String stdName);

    @Modifying
    @Query(value = "UPDATE STADIUM SET STD_LINK = :stdLink, STD_PN = :stdPn, STD_IMG_URL = :stdImgUrl, STD_ST_ADR = :stdAddress, x_cor = :xCor, y_cor = :yCor WHERE STD_NAME = :stdName", nativeQuery = true)
    void updateByStdName(@Param("stdName") String stdName, @Param("stdLink") String stdLink, @Param("stdPn") String stdPn, @Param("stdImgUrl") String stdImgUrl, @Param("stdAddress") String stdAddress, @Param("xCor") String xCor, @Param("yCor") String yCor);
}
