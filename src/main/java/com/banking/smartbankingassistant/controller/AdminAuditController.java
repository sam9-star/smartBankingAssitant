package com.banking.smartbankingassistant.controller;
 
import com.banking.smartbankingassistant.dto.AuditEntry;
import com.banking.smartbankingassistant.service.AuditService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import java.util.List;
 
@RestController
@RequestMapping("/api/admin")
public class AdminAuditController {
 
    private final AuditService auditService;
 
    public AdminAuditController(AuditService auditService) {
        this.auditService = auditService;
    }
 
    @GetMapping("/audit")
    public List<AuditEntry> getAuditEntries() {
        return auditService.getEntries();
    }
}
 