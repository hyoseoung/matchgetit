package com.matchgetit.backend.dto;

import com.matchgetit.backend.constant.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class PaymentRecordDTO {
    private MemberDTO userId;
    private Long paymentNumber;
    private LocalDateTime transactionDateTime;
    private LocalDateTime cancellationDateTime;
    private String gameNumber;
    private PaymentStatus paymentStatus;
    private String userName;
}
