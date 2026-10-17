package com.banking.smartbankingassistant.dto;
 
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
 
public record EnquiryRequest(
 
    @Size(max = 40, message = "Customer reference must not exceed 40 characters")
    String customerReference,
 
    @NotBlank(message = "Channel is required")
    @Pattern(
        regexp = "WEB|MOBILE|BRANCH|CALL",
        message = "Channel must be WEB, MOBILE, BRANCH, or CALL"
    )
    String channel,
 
    @NotBlank(message = "Message is required")
    @Size(
        min = 10,
        max = 1500,
        message = "Message must contain 10 to 1500 characters"
    )
    String message
 
) {}
 