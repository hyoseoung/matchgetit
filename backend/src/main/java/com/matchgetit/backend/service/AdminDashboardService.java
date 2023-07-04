package com.matchgetit.backend.service;

import com.matchgetit.backend.constant.AccountState;
import com.matchgetit.backend.constant.EmploymentStatus;
import com.matchgetit.backend.constant.Gender;
import com.matchgetit.backend.constant.LogInType;
import com.matchgetit.backend.entity.ManagerEntity;
import com.matchgetit.backend.entity.MemberEntity;
import com.matchgetit.backend.repository.ManagerRepository;
import com.matchgetit.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminDashboardService {
    private final MemberRepository userRepository;
    private final ManagerRepository managerRepository;

    public void createManagers() {
        for (int i=21; i<=30; i++) {
            MemberEntity member = new MemberEntity();
            ManagerEntity manager = new ManagerEntity();
            member.setName("테스터"+i);
            member.setEmail("tester"+i+"@test.com");
            member.setPn("010-9876-43"+i);
            member.setPw("1234");
            member.setGender((i%2==0) ? Gender.FEMALE : Gender.MALE);
            member.setBDay(Date.valueOf("1998-06-"+i));
            member.setRating(100L);
//            member.setRegDate(Date.valueOf("2023-05-30"));
            member.setRegDate(Date.valueOf(LocalDate.now()));
            member.setLastConnectionDate(Date.valueOf(LocalDate.now()));
            member.setAccountState(AccountState.ACTIVE);
            member.setLoginType(LogInType.MANAGER);
            manager.setUser(member);
            manager.setRegistrationDate(LocalDateTime.now());
            manager.setManagerImage("https://");
            manager.setEmploymentStatus(EmploymentStatus.active);

            switch (i) {
                case 24, 27 -> manager.setEmploymentStatus(EmploymentStatus.leave);
                case 28, 30 -> manager.setTeam("A");
            }

            userRepository.save(member);
            managerRepository.save(manager);
        }
    }


    @Transactional(readOnly = true)
    public Map<String, Long> getUserCounts() {
        Map<String, Long> userCounts = new HashMap<>();
        userCounts.put("allUsers", userRepository.count());
        userCounts.put("signUpToday", userRepository.countByRegDate(Date.valueOf(LocalDate.now())));
//        long withdrawal = userRepository.countByRegDateBefore(Date.valueOf(LocalDate.now().minusDays(1)));
//        System.out.println(withdrawal);
        return userCounts;
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getManagerCounts() {
        Map<String, Long> managerCounts = new HashMap<>();
        managerCounts.put("allManagers", managerRepository.count());
        managerCounts.put("assigned", managerRepository.countByTeamIsNotNull());
        managerCounts.put("notAssigned", managerRepository.countByTeamIsNull());
        managerCounts.put("leftManagers", managerRepository.countByEmploymentStatusLike(EmploymentStatus.leave));
        managerCounts.put("bannedManagers", managerRepository.countByEmploymentStatusLike(EmploymentStatus.leave));
        return managerCounts;
    }
}
