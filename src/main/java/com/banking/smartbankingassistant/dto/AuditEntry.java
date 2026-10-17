package com.banking.smartbankingassistant.dto;
 
import java.time.Instant;
 
public record AuditEntry(
        Instant timestamp,
        String operator,
        String action,
        String outcome,
        long durationMs,
        String provider
) {}
 