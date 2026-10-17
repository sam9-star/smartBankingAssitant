package com.banking.smartbankingassistant.controller;

import com.banking.smartbankingassistant.dto.ClassifyResponse;
import com.banking.smartbankingassistant.dto.EnquiryRequest;
import com.banking.smartbankingassistant.dto.SummaryResponse;
import com.banking.smartbankingassistant.service.AIService;
import com.banking.smartbankingassistant.service.AuditService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/enquiries")
public class EnquiryController {

    private final AIService aiService;
    private final AuditService auditService;

    public EnquiryController(AIService service, AuditService auditService) {
        this.aiService = service;
        this.auditService = auditService;
    }

    @PostMapping("/classify")
    public ClassifyResponse classify(@Valid @RequestBody EnquiryRequest request, Authentication authentication) {
        long start = System.nanoTime();

        try{
            ClassifyResponse response = aiService.classify(request);
            auditService.record(authentication.getName(), "CLASSIFY", "SUCCESS", elapseMs(start), "OpenAI/gpt-4o-mini");
            return response;
        } catch (RuntimeException ex){
            auditService.record(authentication.getName(), "CLASSIFY", "FAILURE", elapseMs(start), "OpenAI/gpt-4o-mini");
            throw ex;
        }

    }

    @PostMapping("/summarize")
    public SummaryResponse summarize(@Valid @RequestBody EnquiryRequest request, Authentication authentication){

        long start = System.nanoTime();

        try{
            SummaryResponse response = aiService.summarize(request);
            auditService.record(authentication.getName(), "SUMMARIZE", "SUCCESS", elapseMs(start), "OpenAI/gpt-4o-mini");
            return response;
        } catch (RuntimeException ex){
            auditService.record(authentication.getName(), "SUMMARIZE", "FAILURE", elapseMs(start), "OpenAI/gpt-4o-mini");
            throw ex;
        }
    }

    private long elapseMs(long start){
        return (System.nanoTime() - start) / 1000000;
    }

}
