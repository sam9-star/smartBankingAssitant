package com.banking.smartbankingassistant.controller;

import com.banking.smartbankingassistant.dto.AskRequest;
import com.banking.smartbankingassistant.dto.AskResponse;
import com.banking.smartbankingassistant.service.AIService;
import com.banking.smartbankingassistant.service.AuditService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/assistant")
public class AssistantController {

    public final AIService aiService;
    private final AuditService auditService;

    public AssistantController(AIService service, AuditService auditService) {
        this.aiService = service;
        this.auditService = auditService;
    }

    @PostMapping("/ask")
    public AskResponse ask(@Valid @RequestBody AskRequest request, Authentication authentication) {

        long start = System.nanoTime();

        try{
            AskResponse response = aiService.ask(request);
            auditService.record(authentication.getName(), "ASK", "SUCCESS", elapseMs(start), "OpenAI/gpt-4o-mini");
            return response;
        } catch (RuntimeException ex){
            auditService.record(authentication.getName(), "ASK", "FAILURE", elapseMs(start), "OpenAI/gpt-4o-mini");
            throw ex;
        }
    }

    private long elapseMs(long start){
        return (System.nanoTime() - start) / 1000000;
    }

}
