package com.matchgetit.backend.repository;

import com.matchgetit.backend.constant.PaymentStatus;
import com.matchgetit.backend.entity.PaymentRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PaymentRecordRepository extends JpaRepository<PaymentRecord, Long> {
    // 회원 번호로 레코드를 조회하는 메서드
    List<PaymentRecord> findByUserNumber(String userNumber);
    List<PaymentRecord> findByUserNumberContainingIgnoreCase(String userNumber);

    // 결제 상태로 레코드를 조회하는 메서드
    List<PaymentRecord> findByPaymentStatus(PaymentStatus paymentStatus);
    List<PaymentRecord> findByPaymentStatusIn(List<PaymentStatus> statuses);

    // 거래 일자 범위로 레코드를 조회하는 메서드
    List<PaymentRecord> findByTransactionDateBetween(Date startDate, Date endDate);

    // 회원 이름으로 레코드를 조회하는 메서드
    List<PaymentRecord> findByMemberNameContaining(String name);

    // 회원 번호로 레코드를 조회하는 메서드
    List<PaymentRecord> findByMemberNumberContaining(String number);

    // 게임 번호로 레코드를 조회하는 메서드
    List<PaymentRecord> findByGameNumberContaining(String number);

    // 추가적인 메서드 정의 가능
}

