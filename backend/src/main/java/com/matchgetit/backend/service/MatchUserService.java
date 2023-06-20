package com.matchgetit.backend.service;

import com.matchgetit.backend.constant.AccountType;
import com.matchgetit.backend.constant.Gender;
import com.matchgetit.backend.constant.Proficiency;
import com.matchgetit.backend.entity.MatchUserEntity;
import com.matchgetit.backend.repository.MatchUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MatchUserService {
    @Autowired
    private MatchUserRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void signUp(String email, String password, String name, String pn, Gender gender, Proficiency proficiency) {
        // 이미 존재하는 사용자인지 확인
        if (memberRepository.findByEmail(email) != null) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        MatchUserEntity member = new MatchUserEntity();
        member.setEmail(email);
        member.setPassword(passwordEncoder.encode(password)); // 비밀번호 암호화
        member.setName(name);
        member.setPn(pn);
        member.setGender(gender);
        member.setProficiency(proficiency);
        member.setAccountType(AccountType.NORMAL);
        memberRepository.save(member);
    }

    public MatchUserEntity login(String email, String password) {
        MatchUserEntity member = memberRepository.findByEmail(email);

        if (member == null) {
            throw new RuntimeException("계정 정보가 일치하지 않습니다");
        }

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new RuntimeException("계정 정보가 일치하지 않습니다");
        }

        return member;
    }
}
