package com.banking.smartbankingassistant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AskRequest(
        @NotBlank(message = "Question is required")
        @Size(message = "Question must contain 5 to 1000 characters")
        String question
) {}
