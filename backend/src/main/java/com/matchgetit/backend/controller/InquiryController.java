package com.matchgetit.backend.controller;

import com.matchgetit.backend.dto.InquiryDTO;
import com.matchgetit.backend.service.InquiryService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/MatchGetIt/admin")
@RequiredArgsConstructor
public class InquiryController {
    private final InquiryService inquiryService;

    @PostConstruct
    public void createInquiries() {
        inquiryService.createInquiries();
    }


    @GetMapping("/inquiryBoard")
    public String board(Model model) {
        List<InquiryDTO> inquiryList = inquiryService.getInquiryList();
        model.addAttribute("inquiryList", inquiryList);
        return "inquiry/InquiryBoard";
    }

    @GetMapping("/inquiry/{inquiryId}")
    public String view(Model model, @PathVariable Long inquiryId) {
        try {
            InquiryDTO inquiryDTO = inquiryService.getInquiry(inquiryId);
            model.addAttribute("post", inquiryDTO);
        }
        catch (EntityNotFoundException e) {
            model.addAttribute("post", new InquiryDTO());
        }
        return "inquiry/InquiryPost";
    }

    @GetMapping("/test")
    public String test() {
        return "inquiry/temp5";
    }

}