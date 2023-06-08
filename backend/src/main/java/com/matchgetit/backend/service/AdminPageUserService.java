package com.matchgetit.backend.service;

import com.matchgetit.backend.dto.AdminPageUserDTO;
import com.matchgetit.backend.entity.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import com.matchgetit.backend.repository.UserRepository3;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminPageUserService {
    private final UserRepository3 userRepository;

    public void createUsers() {
        for (int i=1; i<10; i++) {
            User user = new User();
            user.setName("테스터"+i);
            user.setEmail("tester"+i+"@test.com");
            user.setPhoneNum("010-1234-567"+i);
            user.setPassword("1234");
            user.setGender((i%2==0)?"여자":"남자");
            user.setBirthday(Date.valueOf("1998-06-0"+i));
            user.setScore("100");
            user.setRegDate(Date.valueOf("2023-05-30"));
            user.setLoginDate(Date.valueOf(LocalDate.now()));
            user.setState("활동 중");
            userRepository.save(user);
        }
    }

    @Transactional(readOnly = true)
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public AdminPageUserDTO getUserInfo(Long id) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return AdminPageUserDTO.of(user);
    }

    public Long updateUserInfo(AdminPageUserDTO userDto) {
        User user = userRepository.findById(userDto.getId()).orElseThrow(EntityNotFoundException::new);
        user.updateUser(userDto);
        return user.getId();
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        userRepository.delete(user);
    }
}
