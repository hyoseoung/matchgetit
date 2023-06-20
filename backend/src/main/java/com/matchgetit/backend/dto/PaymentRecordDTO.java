package com.matchgetit.backend.dto;

import com.matchgetit.backend.constant.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class PaymentRecordDTO {

    private String paymentNumber;
    private LocalDateTime transactionDateTime;
    private LocalDateTime cancellationDateTime;
    private String gameNumber;
    private String memberNumber;
    private String memberName;
    private PaymentStatus paymentStatus;
}
