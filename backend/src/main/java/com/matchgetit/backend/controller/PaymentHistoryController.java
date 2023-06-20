package com.matchgetit.backend.controller;

import com.matchgetit.backend.constant.PaymentStatus;
import com.matchgetit.backend.entity.PaymentRecord;
import com.matchgetit.backend.service.PaymentHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PaymentHistoryController {

    private final PaymentHistoryService paymentHistoryService;

    @Autowired
    public PaymentHistoryController(PaymentHistoryService paymentHistoryService) {
        this.paymentHistoryService = paymentHistoryService;
    }

    @GetMapping("/payment-history")
    public String getPaymentHistory(@RequestParam(name = "startDate", required = false) Date startDate,
                                    @RequestParam(name = "endDate", required = false) Date endDate,
                                    @RequestParam(name = "searchCondition", required = false) String searchCondition,
                                    @RequestParam(name = "searchKeyword", required = false) String searchKeyword,
                                    @RequestParam(name = "paymentStatus", required = false) List<String> paymentStatus,
                                    Model model) {
        List<PaymentRecord> paymentRecords;

        if (searchCondition != null && searchKeyword != null) {
            // 검색 조건과 키워드가 지정된 경우
            paymentRecords = paymentHistoryService.getPaymentRecordsBySearchCondition(searchCondition, searchKeyword);
        } else if (startDate != null && endDate != null) {
            // 조회 기간이 지정된 경우
            paymentRecords = paymentHistoryService.getPaymentRecordsByDateRange(startDate, endDate);
        } else if (paymentStatus != null && !paymentStatus.isEmpty()) {
            // 거래 상태가 지정된 경우
            List<PaymentStatus> paymentStatusList = new ArrayList<>();
            for (String status : paymentStatus) {
                paymentStatusList.add(PaymentStatus.valueOf(status));
            }
            paymentRecords = paymentHistoryService.getPaymentRecordsByStatus(paymentStatusList);
        } else {
            // 조회 기간, 검색 조건, 거래 상태가 지정되지 않은 경우, 모든 결제 내역 조회
            paymentRecords = paymentHistoryService.getPaymentRecords();
        }

        model.addAttribute("paymentRecords", paymentRecords);
        return "PaymentHistory";
    }
}