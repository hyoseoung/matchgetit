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
public class PaymentRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String userNumber; // 회원 번호

    @Column
    private Long paymentNumber; // 결제 번호
    @Column
    private LocalDateTime transactionDate; // 거래 일시
    @Column
    private LocalDateTime cancelDate; // 취소 일시
    @Column
    private String gameNumber; // 경기 번호
    @Column
    private String userName; // 회원 이름
    @Column
    @Enumerated(EnumType.STRING)
    private PaymentStatus transactionStatus; // 거래 상태
}
