package com.matchgetit.backend.controller;

import com.matchgetit.backend.constant.PaymentStatus;
import com.matchgetit.backend.dto.MemberDTO;
import com.matchgetit.backend.dto.PaymentRecordDTO;
import com.matchgetit.backend.entity.MemberEntity;
import com.matchgetit.backend.entity.PaymentRecordEntity;
import com.matchgetit.backend.repository.MemberRepository;
import com.matchgetit.backend.service.MemberService;
import com.matchgetit.backend.service.PaymentHistoryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/matchGetIt/payment")
@AllArgsConstructor
public class PaymentHistoryController {

    private final PaymentHistoryService paymentHistoryService;
    private final MemberService memberService;


    @GetMapping("/list")
    public String getPaymentUserList(Model model) {
        List<MemberDTO> userList = memberService.getAllMembers();
        List<PaymentRecordDTO> paymentRecordDTOList = new ArrayList<>();

        for (MemberDTO user : userList) {
            List<PaymentRecordEntity> paymentRecordEntityList = user.getPaymentRecordEntityList();
            if (paymentRecordEntityList != null && !paymentRecordEntityList.isEmpty()) {
                PaymentRecordEntity paymentRecordEntity = paymentRecordEntityList.get(0);
                PaymentRecordDTO paymentRecordDTO = new PaymentRecordDTO();
                paymentRecordDTO.setPaymentNumber(paymentRecordEntity.getPaymentNumber());
                paymentRecordDTO.setGameNumber(paymentRecordEntity.getGameNumber());
                paymentRecordDTO.setTransactionDateTime(paymentRecordEntity.getTransactionDate());
                paymentRecordDTO.setCancellationDateTime(paymentRecordEntity.getCancelDate());
                paymentRecordDTO.setPaymentStatus(paymentRecordEntity.getTransactionStatus());

                paymentRecordDTOList.add(paymentRecordDTO);
            }
        }

        model.addAttribute("userList", userList);
        model.addAttribute("paymentRecordList", paymentRecordDTOList);

        return "admin/pages/PaymentHistory/PaymentHistory";
    }




}