package com.matchgetit.backend.service;

import com.matchgetit.backend.constant.AccountType;
import com.matchgetit.backend.constant.Gender;
import com.matchgetit.backend.constant.LogInType;
import com.matchgetit.backend.constant.Proficiency;
import com.matchgetit.backend.dto.MemberDTO;
import com.matchgetit.backend.dto.PartyDTO;
import com.matchgetit.backend.entity.MemberEntity;
import com.matchgetit.backend.entity.PartyEntity;
import com.matchgetit.backend.repository.MemberRepository;
import com.matchgetit.backend.util.FormatDate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void signUp(String email, String password, String name, String pn, String birthDay, Gender gender, Proficiency proficiency, AccountType accountType) {
        // 이미 존재하는 사용자인지 확인
        if (memberRepository.findByEmail(email) != null) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        MemberEntity member = new MemberEntity();
        member.setEmail(email);
        member.setPw(passwordEncoder.encode(password)); // 비밀번호 암호화
        member.setName(name);
        member.setPn(pn);
        member.setBDay(FormatDate.parseDate(birthDay));
        member.setGender(gender);
        member.setPrfcn(proficiency);
        member.setAccountType(accountType);
        member.setLoginType(LogInType.NORMAL);
        if(proficiency == Proficiency.ADVANCED)member.setRating(900);
        else if(proficiency == Proficiency.MIDDLE)member.setRating(700);
        else member.setRating(500);
        member.setRegDate(new Date());
        memberRepository.save(member);
    }
    public void socialSignUp(String email, String name, String pn, String birthDay, Gender gender, Proficiency proficiency,AccountType accountType,LogInType logInType) {
        // 이미 존재하는 사용자인지 확인
        if (memberRepository.findByEmail(email) != null) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }
        MemberEntity member = new MemberEntity();
        member.setEmail(email);
        member.setName(name);
        member.setPn(pn);
        member.setBDay(FormatDate.parseDate(birthDay));
        member.setGender(gender);
        member.setPrfcn(proficiency);
        member.setAccountType(accountType);
        member.setRegDate(new Date());
        member.setLoginType(logInType);
        if(proficiency == Proficiency.ADVANCED)member.setRating(900);
        else if(proficiency == Proficiency.MIDDLE)member.setRating(700);
        else member.setRating(500);
        memberRepository.save(member);
    }

    public MemberDTO login(String email, String password) {
        MemberEntity member = memberRepository.findByEmail(email);

        if (member == null) {
            throw new RuntimeException("계정 정보가 일치하지 않습니다");
        }

        if (!passwordEncoder.matches(password, member.getPw())) {
            throw new RuntimeException("계정 정보가 일치하지 않습니다");
        }
        return new ModelMapper().map(member, MemberDTO.class);
    }
    public MemberDTO findMemberById(int userId){
        ModelMapper mm = new ModelMapper();
        MemberEntity memberEntity= memberRepository.findByUserId(userId);
        if(memberEntity ==null) return null;
        else return mm.map(memberEntity,MemberDTO.class);
    }
    public MemberDTO findMemberByEmail(String email){
        ModelMapper mm = new ModelMapper();
        MemberEntity memberEntity= memberRepository.findByEmail(email);
        if(memberEntity ==null) return null;
        else return mm.map(memberEntity,MemberDTO.class);
    }
    public MemberDTO findMemberByPhoneNumber(String pn){
        ModelMapper mm = new ModelMapper();
        MemberEntity memberEntity= memberRepository.findByPn(pn);
        if(memberEntity ==null) return null;
        else return mm.map(memberEntity,MemberDTO.class);
    }
    public void updateParty(int userId, PartyDTO partyDTO) {
        MemberEntity member = memberRepository.findByUserId(userId);
        ModelMapper modelMapper = new ModelMapper();
        if (member == null) {
            throw new RuntimeException("해당 회원이 존재하지 않습니다.");
        }
        if (partyDTO == null) {
            throw new RuntimeException("해당 파티가 존재하지 않습니다.");
        }
        PartyEntity partyEntity= modelMapper.map(partyDTO,PartyEntity.class);

        member.setPartyEntity(partyEntity);
        memberRepository.save(member);
    }
}
