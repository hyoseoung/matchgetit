package com.matchgetit.backend.service;

import com.matchgetit.backend.constant.AccountState;
import com.matchgetit.backend.constant.Gender;
import com.matchgetit.backend.constant.LogInType;
import com.matchgetit.backend.dto.AdminPageSearchUserDTO;
import com.matchgetit.backend.dto.AdminPageUserDTO;
import com.matchgetit.backend.entity.MemberEntity;
import com.matchgetit.backend.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminPageUserService {
//    private final UserRepository userRepositoryOld;
    private final MemberRepository userRepository;

    public void createUsers() {
        for (int i=1; i<10; i++) {
            MemberEntity member = new MemberEntity();
            member.setName("테스터"+i);
            member.setEmail("tester"+i+"@test.com");
            member.setPn("010-1234-567"+i);
            member.setPw("1234");
            member.setGender((i%2==0) ? Gender.FEMALE : Gender.MALE);
            member.setBDay(Date.valueOf("1998-06-0"+i));
            member.setRating(100L);
//            member.setRegDate(Date.valueOf("2023-05-30"));
            member.setRegDate(Date.valueOf(LocalDate.now()));
            member.setLastConnectionDate(Date.valueOf(LocalDate.now()));
//            member.setState("활동 중");
            member.setAccountState(AccountState.ACTIVE);
            member.setLoginType(LogInType.NORMAL);
            userRepository.save(member);
        }

        for (int i=10; i<=20; i++) {
            MemberEntity member = new MemberEntity();
            member.setName("테스터"+i);
            member.setEmail("tester"+i+"@test.com");
            member.setPn("010-9876-54"+i);
            member.setPw("1234");
            member.setGender((i%2==0) ? Gender.FEMALE : Gender.MALE);
            member.setBDay(Date.valueOf("1998-06-"+i));
            member.setRating(100L);
            member.setRegDate(Date.valueOf(LocalDate.now()));
            member.setLastConnectionDate(Date.valueOf(LocalDate.now()));
//            user.setState("활동 중");
            member.setAccountState(AccountState.ACTIVE);
            member.setLoginType(LogInType.NORMAL);
            if (i == 12 || i == 13) {
                member.setLastConnectionDate(Date.valueOf(LocalDate.now().minusMonths(3)));
//                user.setState("정지");
                member.setAccountState(AccountState.BANNED);
                member.setBanDateStart(Date.valueOf("2023-03-20"));
                member.setBanDateEnd(Date.valueOf("2023-09-20"));
                member.setBanReason("비매너 플레이로 경고 3회 이상 받음");
            }
            else if (i == 15) {
                member.setRegDate(Date.valueOf("2010-10-10"));
                member.setLastConnectionDate(Date.valueOf(LocalDate.now().minusYears(5)));
//                user.setState("휴면");
                member.setAccountState(AccountState.DORMANT);
            }
            userRepository.save(member);
        }
    }

//    @Transactional(readOnly = true)
//    public List<UserEntity> getUserList() {
//        return userRepositoryOld.findAll();
//    }

    @Transactional(readOnly = true)
    public Page<AdminPageUserDTO> getPageableUserList(AdminPageSearchUserDTO searchUserDTO, Pageable pageable) {
//        return userRepositoryOld.getUserListPageBy(searchUserDTO, pageable);
        return userRepository.getUserListPageBy(searchUserDTO, pageable);
    }


    @Transactional(readOnly = true)
    public AdminPageUserDTO getUserInfo(Long id) {
        MemberEntity member = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return AdminPageUserDTO.of(member);
    }

    public Long updateUserInfo(AdminPageUserDTO userDto) {
        MemberEntity member = userRepository.findById(userDto.getId()).orElseThrow(EntityNotFoundException::new);
        member.updateUser(userDto);
        return member.getUserId();
    }

    public void deleteUser(Long userId) {
        MemberEntity member = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        userRepository.delete(member);
    }


    public void banUser(Long userId, Date banDateStart, Date banDateEnd, String banReason) {
        MemberEntity member = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        member.banUser(banDateStart, banDateEnd, banReason);
    }

    public void cancelBan(Long userId) {
        MemberEntity member = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        member.cancelBan();
    }

}
