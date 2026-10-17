package com.banking.smartbankingassistant.dto;
 
import java.math.BigDecimal;
 
public record ClassifyResponse(
        Category category,
        Urgency urgency,
        Sentiment sentiment,
        BigDecimal confidence,
        String rationale
) {
    public enum Category {
        TRANSFER_ISSUE,
        CARD_ISSUE,
        ACCOUNT_SERVICE,
        DIGITAL_BANKING,
        LOAN_QUERY,
        GENERAL_ENQUIRY,
        OTHER
    }
 
    public enum Urgency {
        LOW,
        MEDIUM,
        HIGH
    }
 
    public enum Sentiment {
        POSITIVE,
        NEUTRAL,
        CONCERNED,
        FRUSTRATED
    }
}
 