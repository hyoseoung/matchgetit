package com.matchgetit.backend.entity;

import com.matchgetit.backend.constant.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter @Setter @ToString
public class PaymentRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "paymentNumber")
    private Long paymentNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private MemberEntity member;

    @Column
    private LocalDateTime transactionDate; // 거래 일시

    @Column
    private LocalDateTime cancelDate; // 취소 일시

    @Column
    private String gameNumber; // 경기 번호

    @Column
    @Enumerated(EnumType.STRING)
    private PaymentStatus transactionStatus; // 거래 상태
}