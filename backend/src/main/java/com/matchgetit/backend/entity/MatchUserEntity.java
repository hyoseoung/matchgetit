package com.matchgetit.backend.entity;

import com.matchgetit.backend.constant.AccountType;
import com.matchgetit.backend.constant.Gender;
import com.matchgetit.backend.constant.Proficiency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="match_user")
@Getter
@Setter
public class MatchUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String pn;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    @Enumerated(EnumType.STRING)
    private Proficiency proficiency;

    @Column
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
}
