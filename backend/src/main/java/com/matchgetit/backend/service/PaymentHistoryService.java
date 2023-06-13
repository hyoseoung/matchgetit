package com.matchgetit.backend.service;

import com.matchgetit.backend.constant.PaymentStatus;
import com.matchgetit.backend.entity.PaymentRecord;
import com.matchgetit.backend.repository.PaymentRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaymentHistoryService {

    private final PaymentRecordRepository paymentRecordRepository;

    @Autowired
    public PaymentHistoryService(PaymentRecordRepository paymentRecordRepository) {
        this.paymentRecordRepository = paymentRecordRepository;
    }

    public List<PaymentRecord> getPaymentRecords() {
        return paymentRecordRepository.findAll();
    }

    public List<PaymentRecord> getPaymentRecordsByDateRange(Date startDate, Date endDate) {
        return paymentRecordRepository.findByTransactionDateBetween(startDate, endDate);
    }

    public List<PaymentRecord> getPaymentRecordsBySearchCondition(String condition, String keyword) {
        if (condition.equals("name")) {
            return paymentRecordRepository.findByMemberNameContaining(keyword);
        } else if (condition.equals("userNumber")) {
            return paymentRecordRepository.findByUserNumberContainingIgnoreCase(keyword);
        } else if (condition.equals("gameNumber")) {
            return paymentRecordRepository.findByGameNumberContaining(keyword);
        }
        return null;
    }

    public List<PaymentRecord> getPaymentRecordsByStatus(List<PaymentStatus> statuses) {
        return paymentRecordRepository.findByPaymentStatusIn(statuses);
    }
}
