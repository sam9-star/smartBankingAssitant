package com.banking.smartbankingassistant.service;
 
import com.banking.smartbankingassistant.dto.AuditEntry;
import org.springframework.stereotype.Service;
 
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
 
@Service
public class AuditService {
 
    private final List<AuditEntry> entries =
            new CopyOnWriteArrayList<>();
 
    public void record(
            String operator,
            String action,
            String outcome,
            long durationMs,
            String provider) {
 
        entries.add(new AuditEntry(
                Instant.now(),
                safe(operator),
                safe(action),
                safe(outcome),
                Math.max(0, durationMs),
                safe(provider)
        ));
    }
 
    public List<AuditEntry> getEntries() {
        return List.copyOf(entries);
    }
 
    private String safe(String value) {
        return value == null ? "unknown" : value;
    }
}
 